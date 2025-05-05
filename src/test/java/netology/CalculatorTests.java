package netology;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class CalculatorTests {

    static Calculator calc;

    @BeforeAll
    static void setUp() {
        calc = Calculator.instance.get();
    }

    @ParameterizedTest
    @CsvSource({
            "3, 4, 7",
            "4, 3, 7",
            "11, 0, 11",
            "0, 11, 11",
            "-5, 5, 0",
            "10, -100, -90",
    })
    public void testPlus(Integer a, Integer b, Integer expected) {
        // Act
        Integer result = calc.plus.apply(a, b);
        // Assert
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 4, -1",
            "4, 3, 1",
            "11, 0, 11",
            "0, -11, 11",
            "-5, -5, 0",
            "-10, 100, -110",
    })
    public void testMinus(Integer a, Integer b, Integer expected) {
        // Act
        Integer result = calc.minus.apply(a, b);
        // Assert
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 4, 12",
            "4, 3, 12",
            "11, 0, 0",
            "0, -11, 0",
            "-5, -5, 25",
            "-10, 100, -1000",
    })
    public void testMultiply(Integer a, Integer b, Integer expected) {
        // Act
        Integer result = calc.multiply.apply(a, b);
        // Assert
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "12, 3, 4",
            "12, 4, 3",
            "0, 11, 0",
            "0, -11, 0",
            "-5, -5, 1",
            "-10, 100, 0",
    })
    public void testDivide(Integer a, Integer b, Integer expected) {
        // Act
        Integer result = calc.divide.apply(a, b);
        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testDivideByZero() {
        // Arrange
        Integer a = 10;
        Integer b = 0;
        // Act & Assert
        Assertions.assertThrows(RuntimeException.class, () -> calc.divide.apply(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 4",
            "-2, 4",
            "0, 0",
    })
    public void testPow2(Integer a, Integer expected) {
        // Act
        Integer result = calc.pow2.apply(a);
        // Assert
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 2",
            "-2, 2",
            "0, 0",
    })
    public void testAbs(Integer a, Integer expected) {
        // Act
        Integer result = calc.abs.apply(a);
        // Assert
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "2, true",
            "-2, false",
            "0, false",
    })
    public void testIsPositive(Integer a, boolean expected) {
        // Act
        boolean result = calc.isPositive.test(a);
        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testPrintln() throws Exception {
        Integer a = 1234567890;
        String expected = "1234567890";

        String text = SystemLambda.tapSystemOut(() -> Calculator.println.accept(a));
        Assertions.assertEquals(expected, text.trim());
    }
}