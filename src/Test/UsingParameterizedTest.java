package src.Test;

// creds on this one to : https://www.vogella.com/tutorials/JUnit/article.html#google_vignette

import org.junit.jupiter.params.ParamaterizedTest;
import org.junit.jupiter.provider.MethodSource;

import static org.junit.Assert.*;

public class UsingParameterizedTest {

    public static int[][] data() {
        return new int[][] { { 1 , 2, 2 }, { 5, 3, 15 }, { 121, 4, 484 } };
    }

    @ParameterizedTest
    @MethodSource(value =  "data")
    void testWithStringParameter(int[] data) {
        MyClass tester = new MyClass();
        int m1 = data[0];
        int m2 = data[1];
        int expected = data[2];
        assertEquals(expected, tester.multiply(m1, m2));
    }

    // class to be tested
    class MyClass {
        public int multiply(int i, int j) {
            return i * j;
        }
    }
}