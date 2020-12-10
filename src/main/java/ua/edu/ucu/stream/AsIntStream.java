package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import javax.swing.text.html.HTMLDocument;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AsIntStream implements IntStream {
    private Iterator iterator;
    private int[] values;

    private AsIntStream(int... values) {
        this.values = values;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }


    public boolean isEmpty() {
        return this.count() == 0;
    }

    @Override
    public Double average() {
        if (this.isEmpty()) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        Double averageMean = 0.0;
        Double sumValues = new Double(this.sum());
        long sizeValues = this.count();
        averageMean = (sumValues / sizeValues);
        return averageMean;
    }

    @Override
    public Integer max() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        Integer maxValue = Integer.MIN_VALUE;
        for (Integer elem : this.values) {
            if (elem > maxValue) {
                maxValue = elem;
            }
        }
        return maxValue;
    }

    @Override
    public Integer min() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        Integer minValue = Integer.MAX_VALUE;
        for (Integer elem : this.values) {
            if (elem < minValue) {
                minValue = elem;
            }
        }
        return minValue;
    }

    @Override
    public long count() {
        int count = 0;
        for (Object elem : values) {
            count += 1;
        }
        return count;
    }

    @Override
    public Integer sum() {
        if (this.isEmpty()) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        int sumMean = 0;
        for (int elem : values) {
            sumMean += elem;
        }
        return sumMean;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        if (this.isEmpty()) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        int counter = 0;
        for (Integer elem : values) {
            if (predicate.test(elem)) {
                counter += 1;
            }
        }
        int[] resultArray = new int[counter];
        int index = 0;
        for (Integer elem : values) {
            if (predicate.test(elem)) {
                resultArray[index] = elem;
                index += 1;
            }
        }
        return new AsIntStream(resultArray);
    }

    @Override
    public void forEach(IntConsumer action) {
        if (this.isEmpty()) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        for (Integer elem : values) {
            action.accept(elem);
        }

    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        if (this.isEmpty()) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        int[] valuesCopy = Arrays.copyOf(this.values, (int) this.count());
        int counter = 0;
        for (Integer elem : values) {
            valuesCopy[counter] = mapper.apply(elem);
            counter += 1;
        }
        return new AsIntStream(valuesCopy);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {

        IntStream[] listOfStreams = new IntStream[this.values.length];
        IntStream iterStream;
        int size = 0;
        for (int i = 0; i < values.length; i++) {
            iterStream = func.applyAsIntStream(values[i]);
            listOfStreams[i] = iterStream;
            size += iterStream.count();
        }
        int[] totalStream = new int[size];
        int index = 0;
        for (int i = 0; i < values.length; i++) {
            for (int count = 0; count < listOfStreams[i].count(); count++) {
                totalStream[index + count] = listOfStreams[i].toArray()[count];
            }
            index += listOfStreams[i].count();
        }
        return new AsIntStream(totalStream);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        if (this.isEmpty()) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        int res = identity;
        for (int i = 0; i < this.count() - 1; i++) {
            res = op.apply(res, values[i + 1]);
        }
        return res;
    }

    @Override
    public int[] toArray() {
        if (this.isEmpty()) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        int[] arr = Arrays.copyOf(this.values, (int) this.count());
        return arr;
    }

}
