package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {

    public static final String SAUCE = "SAUCE";
    public static final String FILLING = "FILLING";

    static Stream<Arguments> ingredientTypeData() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE, SAUCE),
                Arguments.of(IngredientType.FILLING, FILLING)
        );
    }

    @ParameterizedTest(name = "Проверка типа ингредиента {1}")
    @MethodSource("ingredientTypeData")
    @DisplayName("Проверка типа ингредиента")
    public void ingredientTypeTest(IngredientType ingredientType, String expectedType) {
        assertEquals(expectedType, ingredientType.toString(),
                "Ожидался тип ингредиента " + expectedType + ", но получили " + ingredientType);
    }
}