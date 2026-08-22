package praktikum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.IngredientType;
import praktikum.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientTypeTest.FILLING;

public class BurgerTest {

@ParameterizedTest
@DisplayName("Получаем цену ингредиента")
@CsvSource({
        "FILLING, hot sauce, 100.0",
        "FILLING, sour cream, 200.0",
        "FILLING, chili sauce, 300.0",
        "SAUCE, cutlet, 100.0",
        "SAUCE, dinosaur, 200.0",
        "SAUCE, sausage, 300.0"
})
    public void getPriceTest(IngredientType type, String name, float price) {
    Ingredient ingredient = new Ingredient(type, name, price);
    float actualPrice = ingredient.getPrice();
    assertEquals(price, actualPrice, 0,
            "Ожидаемая цена ингредиента: " + price + ", но получили: " + actualPrice);
}
@ParameterizedTest
@DisplayName("Получаем имя ингредиента")
@CsvSource({
            "FILLING, hot sauce, 100.0",
            "FILLING, sour cream, 200.0",
            "FILLING, chili sauce, 300.0",
            "SAUCE, cutlet, 100.0",
            "SAUCE, dinosaur, 200.0",
            "SAUCE, sausage, 300.0"
    })
    public void getNameTest(IngredientType type, String name, float price) {
    Ingredient ingredient = new Ingredient(type, name, price);
    String actualName = ingredient.getName();
    assertEquals(name, actualName,
            "Ожидаемое имя ингредиента: " + name + ", но получили: " + actualName);
}
@ParameterizedTest
@DisplayName("Получаем тип ингредиента")
@CsvSource({
            "FILLING, hot sauce, 100.0",
            "FILLING, sour cream, 200.0",
            "FILLING, chili sauce, 300.0",
            "SAUCE, cutlet, 100.0",
            "SAUCE, dinosaur, 200.0",
            "SAUCE, sausage, 300.0"
    })
    public void getIngredientTypeTest(IngredientType type, String name, float price) {
    Ingredient ingredient = new Ingredient(type, name, price);
    IngredientType actualType = ingredient.getType();
    assertEquals(type, actualType,
            "Ожидаемый тип ингредиента: " + type + ", но получили: " + actualType);
}
}
