package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/main")
@Getter
public class ServiceServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Order> orders = new ArrayList<>();
    private int indexOrder = 1;
    private int indexProduct = 1;

    {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Order order = new Order();
        List<Product> products = List.of(new Product(putIndexOfProduct(),"Cola", 250), new Product(putIndexOfProduct(), "Pizza", 300), new Product(putIndexOfProduct(),"Pepsi", 350));
        order.setId(putIndexOfOrder());
        order.setProducts(products);
        order.setCost(products.stream().mapToDouble(Product::getCost).sum());
        order.setLocalDateTime(LocalDateTime.now());
        orders.add(order);
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter printWriter = resp.getWriter()){
            String id = req.getParameter("id");
            if (id == null){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                printWriter.println("error: bad request");
                return;
            }
            else if (id.isEmpty()){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                printWriter.println("error: bad request");
                return;
            }
            Optional<Order>  order = orders.stream().filter(i -> i.getId() == Integer.parseInt(id)).findFirst();
            if (!order.isPresent()){
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                printWriter.println("error: not found");
                return;
            }
            printWriter.println(objectMapper.writeValueAsString(order.get()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            String json = req.getReader().lines().collect(Collectors.joining());
            Order order = objectMapper.readValue(json, Order.class);
            order.setId(putIndexOfOrder());
            order.setCost(order.getProducts().stream().mapToDouble(Product::getCost).sum());
            orders.add(order);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.setContentType("application/json");
            writer.println(objectMapper.writeValueAsString(order));
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("application/json");
            resp.getWriter().println("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            String json = req.getReader().lines().collect(Collectors.joining());
            Order newOrder = objectMapper.readValue(json, Order.class);
            Order order = orders.stream().filter(i -> i.getId() == newOrder.getId()).findFirst().get();
            orders.remove(order);
            orders.add(newOrder);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            writer.println(objectMapper.writeValueAsString(newOrder));
        }catch (Exception e){
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()){
            int id = Integer.parseInt(req.getParameter("id"));
            Order order = orders.stream().filter(i -> i.getId() == id).findFirst().get();
            orders.remove(order);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            writer.println(objectMapper.writeValueAsString(order));
        }catch (Exception e){
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(e.getMessage());
        }
    }


}
