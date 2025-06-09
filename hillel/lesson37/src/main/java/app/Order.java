package app;


import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Order {

    private int id;
    private LocalDateTime localDateTime;
    private double cost;
    private List<Product> products;

    public Order (){
    }
}
