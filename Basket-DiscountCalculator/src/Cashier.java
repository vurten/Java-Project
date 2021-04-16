import java.util.Arrays;
import java.util.List;

/**
 * Created by William Flageol on 2020-04-30.
 */
public class Cashier {
    private double price;
    private Discount[] discounts;

    public Cashier(int basePrice, Discount[] discounts) {
        this.price = basePrice;
        this.discounts = discounts;
    }

    public double compute(Basket b) {
        if(b.isEmpty()) { return 0.0; }

        Discount[] availables = findAvailableDiscount(b);

        if(availables.length == 0)
            return price * b.howManyBooks();
        else
            return Arrays.stream(availables)
                    .map(d -> compute(b, d))
                    .min(Double::compare).get();
    }

    private double compute(Basket b, Discount d) {
        double local = d.apply(price);
        Basket remaining = d.removePaidBooks(b);
        return local + compute(remaining);
    }

    private Discount[] findAvailableDiscount(Basket b) {
        return Arrays.stream(discounts)
                .filter(d -> d.canBeApplied(b))
                .toArray(Discount[]::new);
    }
}