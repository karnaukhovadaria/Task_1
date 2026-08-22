package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {

    public static final String SAUCE = "SAUCE";
    public static final String FILLING = "FILLING";

    @Test
    @DisplayName("Проверка типа ингредиента соус")
    public void sauceTest() {
        assertEquals(SAUCE, IngredientType.SAUCE.toString(),
                "Ожидался тип ингредиента " + SAUCE + ", но получили " + IngredientType.SAUCE.toString());
    }
    @Test
    @DisplayName("Проверка типа ингредиента начинка")
    public void fillingTest() {
        assertEquals(FILLING, IngredientType.FILLING.toString(),
                "Ожидался тип ингредиента " + FILLING + ", но получили " + IngredientType.FILLING.toString());
    }
}
