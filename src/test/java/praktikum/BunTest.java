package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    private static final float DELTA = 0.0f;

    @ParameterizedTest
    @DisplayName("Получить название булочки")
    @CsvSource({
            "black bun, 100.0",
            "white bun, 200.0",
            "red bun, 300.0"
    })
    public void getNameTest(String name, float price) {
        Bun bun = new Bun(name, price);
        String actualName = bun.getName();
        assertEquals(name, actualName,
                "Имя булочки: " + name + ", но получили: " + actualName);
    }

    @ParameterizedTest
    @DisplayName("Получить цену булочки")
    @CsvSource({
            "black bun, 100.0",
            "white bun, 200.0",
            "red bun, 300.0"
    })
    public void getPriceTest(String name, float price) {
        Bun bun = new Bun(name, price);
        float actualPrice = bun.getPrice();
        assertEquals(price, actualPrice, DELTA,
                "Ожидаемая цена булочки: " + price + ", но получили: " + actualPrice);
    }
}