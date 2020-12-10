package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

/**
 * @author andrii
 */
public class StreamAppTest {

    private IntStream intStream;
    private IntStream emptyStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);

        int[] emptyArr = {};
        emptyStream = AsIntStream.of(emptyArr);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAverageWithEmptyArray() throws UnsupportedOperationException {
        emptyStream.average();
    }

    @Test
    public void testAverage() {
        System.out.println("Average");
        Double expResult = 1.0;
        Double result = intStream.average();
        assertEquals(expResult, result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSumWithEmptyArray() throws UnsupportedOperationException {
        emptyStream.sum();
    }

    @Test
    public void testSum() {
        System.out.println("Sum");
        int expResult = 5;
        int result = intStream.sum();
        assertEquals(expResult, result);
    }

    @Test
    public void testCount() {
        System.out.println("Count");
        long expResult = 5;
        long result = intStream.count();
        assertEquals(expResult, result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMaxWithEmptyArray() throws UnsupportedOperationException {
        emptyStream.max();
    }

    @Test
    public void testMax() {
        System.out.println("Max");
        int expResult = 3;
        int result = intStream.max();
        assertEquals(expResult, result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMinWithEmptyArray() throws UnsupportedOperationException {
        emptyStream.min();
    }

    @Test
    public void testMin() {
        System.out.println("Min");
        int expResult = -1;
        int result = intStream.min();
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamOperations() {
        System.out.println("streamOperations");
        int expResult = 42;
        int result = StreamApp.streamOperations(intStream);
        assertEquals(expResult, result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testToArrayWithEmptyArray() throws UnsupportedOperationException {
        emptyStream.toArray();
    }

    @Test
    public void testStreamToArray() {
        System.out.println("streamToArray");
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = StreamApp.streamToArray(intStream);
        assertArrayEquals(expResult, result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testStreamForEachWithEmptyArray() throws UnsupportedOperationException {
        StreamApp.streamForEach(emptyStream);
    }

    @Test
    public void testStreamForEach() {
        System.out.println("streamForEach");
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStream);
        assertEquals(expResult, result);

    }
}

