package ru.oz.mytutors.matorin.refactoring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    String symbol;
    Integer price;
    int quantity;
    Direction direction;

    enum Direction {
        BUY,
        SELL
    }

    @Deprecated
    public static Order buy(String symbol, Integer price, int quantity) {
        return new Order(symbol, price, quantity, Direction.BUY);
    }

    @Deprecated
    public static Order sell(String symbol, Integer price, int quantity) {
        return new Order(symbol, price, quantity, Direction.BUY);
    }

    @Builder(builderClassName = "DefaultBuilder", builderMethodName = "full")
    public static Order defaultBuilder(String symbol, Integer price, int quantity, Direction direction) {
        return new Order(symbol, price, quantity, direction);
    }

    @Builder(builderClassName = "CustomOrderBuilder", builderMethodName = "buyOrder")
    public static Order buyOrderBuilder(String symbol, Integer price, int quantity) {
        return new Order(symbol, price, quantity, Direction.BUY);
    }

    @Builder(builderClassName = "CustomOrderBuilder", builderMethodName = "sellOrder")
    public static Order sellOrderBuilder(String symbol, Integer price, int quantity) {
        return new Order(symbol, price, quantity, Direction.SELL);
    }

    public static class CustomOrderBuilder extends DefaultBuilder {

        public void sendTo(Broker broker) {
            // Validates required fields
            // Validate.notBlank(super.name, "Order SYMBOL cannot be null or empty!");
            // Validate.notBlank(super.QUANTITY, "Order QUANTITY cannot be null or empty!");
            // Validate.notBlank(super.DIRECTION, "Order DIRECTION cannot be null or empty!");
            broker.addOrder(this.build());
        }
    }
}
