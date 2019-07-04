package checkout;

import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<Product> products = new ArrayList<>();
    private ArrayList<Offer> availableOffers = new ArrayList<>();
    private int points = 0;
    private int costWithDiscountProduct = 0;
    private int discount = 2;

    public int getTotalCost() {
        int totalCost = 0;
        int totalPrice = 0;
        if (costWithDiscountProduct > 0) {
            for (Product product : this.products) {
                totalPrice += product.price;
            }
            totalCost = totalPrice - costWithDiscountProduct / discount;
        } else {
            for (Product product : this.products) {
                totalCost += product.price;
            }
        }
        return totalCost;
    }

    void addProduct(Product product) {
        products.add(product);
    }

    public int getTotalPoints() {
        return getTotalCost() + points;
    }

    void addPoints(int points) {
        this.points += points;
    }

    int getCostByCategory(Category category) {
        return products.stream()
                .filter(p -> p.category == category)
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
    }

    void getCostWithDiscountOffer(String name) {
        costWithDiscountProduct = products.stream()
                .filter(p -> p.name.equals(name))
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);

    }

    void addOffer(Offer offer) {
        availableOffers.add(offer);
    }
}
