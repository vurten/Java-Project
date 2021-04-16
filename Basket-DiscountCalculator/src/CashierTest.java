import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by William Flageol on 2020-04-30.
 * Chaouki Mohamed CHAM27088802 2020-05-14.
 */
class CashierTest {
    private Cashier cashier;

    @BeforeEach
    void setUp() {
        cashier = new Cashier(
                8,
                new Discount[] {
                        new Discount(2, 0.95),
                        new Discount(3, 0.9),
                        new Discount(4, 0.8),
                        new Discount(5, 0.75)});
    }

    private Basket build(Integer... values) {
        return new Basket(Arrays.stream(values)
                .map(v -> new Book(v))
                .toArray(Book[]::new));
    }

    @Test
    public void simpleCase1() {
        assertEquals(15.2, cashier.compute(build(1, 2)));
    }

    @Test
    public void simpleCase2() {
        assertEquals(21.6, cashier.compute(build(1, 3, 5)));
    }

    @Test
    public void simpleCase3() {
        assertEquals(25.6, cashier.compute(build(1, 2, 3, 5)));
    }

    @Test
    public void simpleCase4() {
        assertEquals(30, cashier.compute(build(1, 2, 3, 4, 5)));
    }

    @Test
    public void edgeCase1() {
        assertEquals(51.2,
                cashier.compute(build(1, 1, 2, 2, 3, 3, 4, 5)));
    }

    @Test
    public void edgeCase2() {
        assertEquals(141.2, cashier.compute(
                build(1, 1, 1, 1, 1,
                        2, 2, 2, 2, 2,
                        3, 3, 3, 3,
                        4, 4, 4, 4, 4,
                        5, 5, 5, 5)));
    }

}