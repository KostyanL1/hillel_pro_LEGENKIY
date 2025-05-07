package org.lesson32.cofeOrderBoat;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class CoffeeOrderBoard {

   private static final Logger LOGGER = LogManager.getLogger(CoffeeOrderBoard.class);

    private BlockingQueue<Order> orders = new LinkedBlockingQueue<>(50);
    private int index;

    public void add(String name){
        index++;
        orders.add(new Order(index, name));
        LOGGER.info("Додано нове замовлення на ім'я {}. Номер замовлення {}.", name, index);
    }

    public void deliver(){
        Order order = orders.poll();
        if (order == null){
            LOGGER.info("Черга порожня.");
        }else {
            LOGGER.info("Замовлення видано в порядку черги на ім'я {}. Номер замовлення {}.", order.getCustomer(), order.getId());
        }

    }

    public void deliver(int numberOfOrder){

        Optional<Order> order = orders.stream().filter(index -> index.getId() == numberOfOrder).findFirst();
        if (order.isPresent()){
            Order order1 = order.get();
            orders.remove(order1);
            LOGGER.info("Замовлення видано поза чергою на ім'я {}. Номер замовлення {}.", order1.getCustomer(), order1.getId());
        }else {
            LOGGER.info("Замовлення не видано поза чергою. Замовлення за номером {} не знайдено.", numberOfOrder);
        }

    }

    public void draw(){

        if (orders.isEmpty()){
            LOGGER.info("Черга порожня.");
            System.out.println("Замовлення закінчились!");
        }else {
            orders.stream().sorted(Comparator.comparing(Order::getId)).forEach(System.out::println);
            LOGGER.info("Список замовлень виведено!");
        }
    }


    public void createOrders(int count ){

        try {

            for (int i = 1; i <= count; i++) {
                add("GOOD");

            }
        }catch (IllegalStateException e){
            LOGGER.error("Виникла помилка з переповненням черги. %c" , e );
        }

    }
}
