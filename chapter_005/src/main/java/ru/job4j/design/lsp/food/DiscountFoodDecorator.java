package ru.job4j.design.lsp.food;

public class DiscountFoodDecorator extends Food {
    private final int discountPercent;

    public DiscountFoodDecorator(Food food, int discountPercent) {
        super(food.getName(), food.getCreateDate(), food.getExpireDate(), food.getPrice());
        this.discountPercent = discountPercent;
    }

    @Override
    public double getPrice() {
        var price = super.getPrice();
        return price - (price / 100) * discountPercent;
    }
}
