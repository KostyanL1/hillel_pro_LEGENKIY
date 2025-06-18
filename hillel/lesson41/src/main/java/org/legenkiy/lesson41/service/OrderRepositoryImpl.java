package org.legenkiy.lesson41.service;

import org.legenkiy.lesson41.model.Order;
import org.legenkiy.lesson41.model.Product;
import org.legenkiy.lesson41.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderRepositoryImpl implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    private List<Order> orders;
    private int indexOrder = 1;
    private int indexProduct = 1;

    {
        this.orders = new ArrayList<>();
        Order order = new Order();
        List<Product> products = List.of(
                new Product(putIndexOfProduct(),"Cola", 250.00), new Product(putIndexOfProduct(), "Pizza", 300.00), new Product(putIndexOfProduct(),"Pepsi", 350.00)
        );
        order.setId(putIndexOfOrder());
        order.setProducts(products);
        order.setTotalCost(products.stream().mapToDouble(Product::getCost).sum());
        order.setCreationDate(LocalDateTime.now());
        orders.add(order);
    }


    @Autowired
    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int putIndexOfProduct(){
        int index = this.indexProduct;
        indexProduct++;
        return index;
    }

    public int putIndexOfOrder(){
        int index = this.indexOrder;
        indexOrder++;
        return index;
    }

    @Override
    public Optional<Order> findOrderById(int id) {
        String sqlOrder = "SELECT * FROM orders WHERE id = ?";
        List<Order> orders = jdbcTemplate.query(sqlOrder, new Object[]{id}, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
            order.setTotalCost(rs.getDouble("total_cost"));
            String sqlProducts = """
                SELECT p.id, p.name, p.cost FROM products p
                JOIN order_products op ON p.id = op.product_id
                WHERE op.order_id = ?
                """;
            List<Product> products = jdbcTemplate.query(sqlProducts, new Object[]{id}, (prs, prRow) ->
                    new Product(prs.getInt("id"), prs.getString("name"), prs.getDouble("cost"))
            );
            order.setProducts(products);
            return order;
        });

        return orders.isEmpty() ? Optional.empty() : Optional.of(orders.get(0));
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT id FROM orders";
        List<Integer> ids = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("id"));
        List<Order> result = new ArrayList<>();
        for (Integer id : ids) {
            findOrderById(id).ifPresent(result::add);
        }
        return result;
    }

    @Override
    public void save(Order order) {
        String sqlOrder = "INSERT INTO orders (id, creation_date, total_cost) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlOrder, order.getId(), order.getCreationDate(), order.getTotalCost());
        for (Product p : order.getProducts()) {
            String insertProduct = "INSERT INTO products (id, name, cost) VALUES (?, ?, ?) ON CONFLICT (id) DO NOTHING";
            jdbcTemplate.update(insertProduct, p.getId(), p.getName(), p.getCost());
            String sqlLink = "INSERT INTO order_products (order_id, product_id) VALUES (?, ?)";
            jdbcTemplate.update(sqlLink, order.getId(), p.getId());
        }
    }

    @Override
    public boolean deleteById(int id) {
        String deleteLinks = "DELETE FROM order_products WHERE order_id = ?";
        String deleteOrder = "DELETE FROM orders WHERE id = ?";
        int affectedRows = jdbcTemplate.update(deleteLinks, id);
        affectedRows += jdbcTemplate.update(deleteOrder, id);
        return affectedRows > 0;
    }

    @Override
    public boolean update(Order order) {
        String updateSql = "UPDATE orders SET creation_date = ?, total_cost = ? WHERE id = ?";
        int affected = jdbcTemplate.update(updateSql, order.getCreationDate(), order.getTotalCost(), order.getId());
        jdbcTemplate.update("DELETE FROM order_products WHERE order_id = ?", order.getId());
        for (Product p : order.getProducts()) {
            String insertProduct = "INSERT INTO products (id, name, cost) VALUES (?, ?, ?) ON CONFLICT (id) DO NOTHING";
            jdbcTemplate.update(insertProduct, p.getId(), p.getName(), p.getCost());

            String insertLink = "INSERT INTO order_products (order_id, product_id) VALUES (?, ?)";
            jdbcTemplate.update(insertLink, order.getId(), p.getId());
        }

        return affected > 0;
    }
}
