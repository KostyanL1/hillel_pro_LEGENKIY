package org.lesson5.app;

public class CalcCostDelivery extends CalcCostBase {
    private static double deliveryPrice = 70;

    public double calcCost(Product product) {
        return product.getPrice()
                + deliveryPrice;
    }

}