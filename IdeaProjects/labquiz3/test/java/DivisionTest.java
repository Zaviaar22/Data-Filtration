import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest {

    // Regular division test cases
    @Test
    void division() {
        assertEquals(2, divide(10, 5)); // Regular case
        assertEquals(0, divide(0, 5));  // Dividing zero
        assertThrows(ArithmeticException.class, () -> divide(5, 0)); // Division by zero
        assertEquals(-2, divide(-10, 5)); // Negative dividend
        assertEquals(-2, divide(10, -5)); // Negative divisor
        assertEquals(2, divide(-10, -5)); // Both negative
    }

    // Division using a loop
    @Test
    void divisionUsingLoop() {
        assertEquals(2, divideUsingLoop(10, 5));
        assertEquals(0, divideUsingLoop(0, 5));
        assertThrows(ArithmeticException.class, () -> divideUsingLoop(5, 0));
        assertEquals(-2, divideUsingLoop(-10, 5));
        assertEquals(-2, divideUsingLoop(10, -5));
        assertEquals(2, divideUsingLoop(-10, -5));
    }

    // Division using recursion
    @Test
    void divisionUsingRecursion() {
        assertEquals(2, divideUsingRecursion(10, 5));
        assertEquals(0, divideUsingRecursion(0, 5));
        assertThrows(ArithmeticException.class, () -> divideUsingRecursion(5, 0));
        assertEquals(-2, divideUsingRecursion(-10, 5));
        assertEquals(-2, divideUsingRecursion(10, -5));
        assertEquals(2, divideUsingRecursion(-10, -5));
    }

    // Division using multiplication
    @Test
    void divisionUsingMultiplication() {
        assertEquals(2, divideUsingMultiplication(10, 5));
        assertEquals(0, divideUsingMultiplication(0, 5));
        assertThrows(ArithmeticException.class, () -> divideUsingMultiplication(5, 0));
        assertEquals(-2, divideUsingMultiplication(-10, 5));
        assertEquals(-2, divideUsingMultiplication(10, -5));
        assertEquals(2, divideUsingMultiplication(-10, -5));
    }

    // Division using bitwise shift
    @Test
    void divisionUsingShift() {
        assertEquals(2, divideUsingShift(10, 5));
        assertEquals(0, divideUsingShift(0, 5));
        assertThrows(ArithmeticException.class, () -> divideUsingShift(5, 0));
        assertEquals(-2, divideUsingShift(-10, 5));
        assertEquals(-2, divideUsingShift(10, -5));
        assertEquals(2, divideUsingShift(-10, -5));
    }

    // Division using logarithms
    @Test
    void divisionUsingLogs() {
        assertEquals(2, divideUsingLogs(10, 5));
        assertEquals(0, divideUsingLogs(0, 5));
        assertThrows(ArithmeticException.class, () -> divideUsingLogs(5, 0));
        assertEquals(-2, divideUsingLogs(-10, 5));
        assertEquals(-2, divideUsingLogs(10, -5));
        assertEquals(2, divideUsingLogs(-10, -5));
    }

    // Division method implementations (for testing purposes)
    int divide(int dividend, int divisor) {
        if (divisor == 0) throw new ArithmeticException("Division by zero");
        return dividend / divisor;
    }

    int divideUsingLoop(int dividend, int divisor) {
        if (divisor == 0) throw new ArithmeticException("Division by zero");
        int quotient = 0;
        int absDividend = Math.abs(dividend);
        int absDivisor = Math.abs(divisor);

        while (absDividend >= absDivisor) {
            absDividend -= absDivisor;
            quotient++;
        }

        return (dividend < 0) ^ (divisor < 0) ? -quotient : quotient;
    }

    int divideUsingRecursion(int dividend, int divisor) {
        if (divisor == 0) throw new ArithmeticException("Division by zero");
        if (Math.abs(dividend) < Math.abs(divisor)) return 0;

        int result = 1 + divideUsingRecursion(Math.abs(dividend) - Math.abs(divisor), Math.abs(divisor));
        return (dividend < 0) ^ (divisor < 0) ? -result : result;
    }

    int divideUsingMultiplication(int dividend, int divisor) {
        if (divisor == 0) throw new ArithmeticException("Division by zero");
        int quotient = 0;
        int product = Math.abs(divisor);

        while (product <= Math.abs(dividend)) {
            product += Math.abs(divisor);
            quotient++;
        }

        return (dividend < 0) ^ (divisor < 0) ? -quotient : quotient;
    }

    int divideUsingShift(int dividend, int divisor) {
        if (divisor == 0) throw new ArithmeticException("Division by zero");
        int quotient = 0;
        int absDividend = Math.abs(dividend);
        int absDivisor = Math.abs(divisor);

        while (absDividend >= absDivisor) {
            int shift = 0;
            while (absDividend >= (absDivisor << shift)) {
                shift++;
            }
            absDividend -= absDivisor << (shift - 1);
            quotient += 1 << (shift - 1);
        }

        return (dividend < 0) ^ (divisor < 0) ? -quotient : quotient;
    }

    int divideUsingLogs(int dividend, int divisor) {
        if (divisor == 0) throw new ArithmeticException("Division by zero");
        if (dividend == 0) return 0;

        int result = (int) (Math.exp(Math.log(Math.abs(dividend)) - Math.log(Math.abs(divisor))));
        return (dividend < 0) ^ (divisor < 0) ? -result : result;
    }
}
