import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import task02.exceptions.IncorrectInfixSequenceException;
import task02.exceptions.IncorrectPostfixSequenceException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static task02.ExpressionCalculator.calculate;

public class ExpressionCalculatorNegativeScenario {

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculate("2+10/(7-7)"));
    }

    @Test
    void testEmptyExpression() {
        IncorrectInfixSequenceException exception = assertThrows(IncorrectInfixSequenceException.class, () -> calculate(" "));
        assertThat(exception.getMessage(), is("input is empty"));
    }

    @Test
    void testInputIsNull() {
        assertThrows(NullPointerException.class, () -> calculate(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2 + + 2 - 1", "2 * 4 + - 2"})
    void testToManyOperationsException(String expression) {
        IncorrectInfixSequenceException exception = assertThrows(IncorrectInfixSequenceException.class, () -> calculate(expression));
        assertThat(exception.getMessage(), is("too many operations together"));

    }

    @ParameterizedTest
    @ValueSource(strings = {"(2 + 1)) -1", " 1+3)-(2"})
    void testTooManyCloseBracket(String expression) {
        IncorrectInfixSequenceException exception = assertThrows(IncorrectInfixSequenceException.class, () -> calculate(expression));
        assertThat(exception.getMessage(), is("too many ')'"));

    }

    @ParameterizedTest
    @ValueSource(strings = {"((2 + 1) -1", " 1+3-(2"})
    void testTooManyOpenBracket(String expression) {
        IncorrectInfixSequenceException exception = assertThrows(IncorrectInfixSequenceException.class, () -> calculate(expression));
        assertThat(exception.getMessage(), is("too many '('"));

    }

    @ParameterizedTest
    @ValueSource(strings = {"1 ^ 1","1+2sdaasd+3"})
    void testNotSupportedSymbol(String expression) {
        IncorrectInfixSequenceException exception = assertThrows(IncorrectInfixSequenceException.class, () -> calculate(expression));
        assertThat(exception.getMessage().contains("not supported symbol "), is(true));

    }

    @ParameterizedTest
    @ValueSource(strings = {"1 + 1 +"})
    void testIncorrectPostfixException(String expression) {
        IncorrectPostfixSequenceException exception = assertThrows(IncorrectPostfixSequenceException.class, () -> calculate(expression));
        assertThat(exception.getMessage(), is("not enough operands"));
    }
}
