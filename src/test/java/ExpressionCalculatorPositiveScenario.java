import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static task02.ExpressionCalculator.calculate;
import static task02.ExpressionCalculator.removeSpaces;

public class ExpressionCalculatorPositiveScenario {

    @ParameterizedTest
    @CsvSource({"1+2+3+4,10", "5+6+10,21", "10+12+100,122"})
    void testAdd(String expression, double result) {
        assertThat(calculate(expression),is(result));
    }

    @ParameterizedTest
    @CsvSource({"10-2-3-4,1", "15-6-10,-1", "0-0-0,0"})
    void testSub(String expression, double result) {
        assertThat(calculate(expression), is(result));
    }

    @ParameterizedTest
    @CsvSource({"10*2*3,60", "1*10,10", "0*10,0"})
    void testMult(String expression, double result) {
        assertThat(calculate(expression),is(result));
    }

    @ParameterizedTest
    @CsvSource({"10/2,5", "1/10,0.1", "0/10,0"})
    void testDiv(String expression, double result) {
        assertThat(calculate(expression), is(result));
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "-100+(3* (55 - 45))/(25-10),-98",
                    "1-5*(10-(-100+ (-50-50)) + 100)/(100-90),-154",
                    "-19 + (10 * 8 + 1)-100/(-10),72"
            }
    )
    void testExpressionCalculatorWithAllOperators(String expression, double result) {
        assertThat(calculate(expression),is(result));
    }

    @ParameterizedTest
    @CsvSource({"10 + 1 + 2    / 100  *2 ,10+1+2/100*2", })
    void testRemoveSpaces(String expression, String result) {
        assertThat(removeSpaces(expression),is(result));
    }
}