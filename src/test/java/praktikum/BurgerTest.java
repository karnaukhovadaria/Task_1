package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredientMock1;
    private Ingredient ingredientMock2;

    // Константа для сравнения чисел с плавающей точкой
    private static final float DELTA = 0.001f;

    @BeforeEach
    public void setUp() {
        burger = new Burger();

        // моки для зависимостей
        bunMock = mock(Bun.class);
        ingredientMock1 = mock(Ingredient.class);
        ingredientMock2 = mock(Ingredient.class);

        // Настраиваем поведение моков
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(ingredientMock1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock1.getName()).thenReturn("hot sauce");
        when(ingredientMock1.getPrice()).thenReturn(100.0f);

        when(ingredientMock2.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock2.getName()).thenReturn("cutlet");
        when(ingredientMock2.getPrice()).thenReturn(200.0f);
    }

    // Тесты для setBuns

    @Test
    @DisplayName("Установка булочки в бургер")
    public void setBunsTest() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun,
                "Ожидалась булочка: " + bunMock + ", но получили: " + burger.bun);
    }

    // Тесты для addIngredient

    @Test
    @DisplayName("Добавление ингредиента в бургер")
    public void addIngredientTest() {
        burger.addIngredient(ingredientMock1);
        assertEquals(1, burger.ingredients.size(),
                "Ожидался 1 ингредиент, но получили: " + burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(0),
                "Ожидался ингредиент: " + ingredientMock1 + ", но получили: " + burger.ingredients.get(0));
    }

    @Test
    @DisplayName("Добавление нескольких ингредиентов в бургер")
    public void addMultipleIngredientsTest() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        assertEquals(2, burger.ingredients.size(),
                "Ожидалось 2 ингредиента, но получили: " + burger.ingredients.size());
    }

    //  Тесты для removeIngredient

    @Test
    @DisplayName("Удаление ингредиента из бургера")
    public void removeIngredientTest() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size(),
                "Ожидался 1 ингредиент после удаления, но получили: " + burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(0),
                "Ожидался ингредиент: " + ingredientMock2 + ", но получили: " + burger.ingredients.get(0));
    }

    @Test
    @DisplayName("Удаление ингредиента по несуществующему индексу")
    public void removeIngredientInvalidIndexTest() {
        burger.addIngredient(ingredientMock1);
        assertThrows(IndexOutOfBoundsException.class,
                () -> burger.removeIngredient(5),
                "Ожидалось исключение IndexOutOfBoundsException");
    }

    // Тесты для moveIngredient

    @Test
    @DisplayName("Перемещение ингредиента в бургере")
    public void moveIngredientTest() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.moveIngredient(0, 1);

        assertEquals(ingredientMock2, burger.ingredients.get(0),
                "Ожидался ингредиент: " + ingredientMock2 + " на позиции 0");
        assertEquals(ingredientMock1, burger.ingredients.get(1),
                "Ожидался ингредиент: " + ingredientMock1 + " на позиции 1");
    }

    @Test
    @DisplayName("Перемещение ингредиента с несуществующим индексом")
    public void moveIngredientInvalidIndexTest() {
        burger.addIngredient(ingredientMock1);
        assertThrows(IndexOutOfBoundsException.class,
                () -> burger.moveIngredient(0, 5),
                "Ожидалось исключение IndexOutOfBoundsException");
    }

    //Тесты для getPrice

    @Test
    @DisplayName("Получение цены бургера без ингредиентов")
    public void getPriceWithoutIngredientsTest() {
        burger.setBuns(bunMock);
        float expectedPrice = 100.0f * 2;
        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice, DELTA,
                "Ожидаемая цена: " + expectedPrice + ", но получили: " + actualPrice);
        verify(bunMock, times(1)).getPrice();
    }

    @Test
    @DisplayName("Получение цены бургера с ингредиентами")
    public void getPriceWithIngredientsTest() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float expectedPrice = 100.0f * 2 + 100.0f + 200.0f;
        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice, DELTA,
                "Ожидаемая цена: " + expectedPrice + ", но получили: " + actualPrice);
        verify(bunMock, times(1)).getPrice();
        verify(ingredientMock1, times(1)).getPrice();
        verify(ingredientMock2, times(1)).getPrice();
    }

    // Тесты для getReceipt

    @Test
    @DisplayName("Получение чека для бургера")
    public void getReceiptTest() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String receipt = burger.getReceipt();

        assertNotNull(receipt, "Чек не должен быть null");
        assertTrue(receipt.contains("black bun"),
                "Чек должен содержать название булочки");
        assertTrue(receipt.contains("hot sauce"),
                "Чек должен содержать название первого ингредиента");
        assertTrue(receipt.contains("cutlet"),
                "Чек должен содержать название второго ингредиента");
        assertTrue(receipt.contains("Price:"),
                "Чек должен содержать цену");
    }

    @Test
    @DisplayName("Получение чека для пустого бургера")
    public void getReceiptEmptyBurgerTest() {
        burger.setBuns(bunMock);

        String receipt = burger.getReceipt();

        assertNotNull(receipt, "Чек не должен быть null");
        assertTrue(receipt.contains("black bun"),
                "Чек должен содержать название булочки");
        assertTrue(receipt.contains("Price:"),
                "Чек должен содержать цену");
        assertFalse(receipt.contains("hot sauce"),
                "Чек не должен содержать названия ингредиентов");
        assertFalse(receipt.contains("cutlet"),
                "Чек не должен содержать названия ингредиентов");
    }
}