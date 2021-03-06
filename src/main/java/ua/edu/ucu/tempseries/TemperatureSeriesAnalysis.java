package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;


public class TemperatureSeriesAnalysis {
    private int tempSize;
    private int tempCapacity;
    private double[] temperatureArray;


    public TemperatureSeriesAnalysis() {
        this.tempCapacity = 1;
        this.temperatureArray = new double[tempCapacity];
        this.tempSize = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        final double MIN_TEMP = -273.0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < MIN_TEMP) {
                throw new InputMismatchException();
            }
        }
        this.tempSize = temperatureSeries.length;
        this.temperatureArray = Arrays.copyOf(temperatureSeries, tempSize);
        this.tempCapacity = tempSize;
    }

    private void isEmpty() {
        if (tempSize == 0) {
            throw new IllegalArgumentException();
        }
    }

    public double average() {
        isEmpty();
        double average = 0;
        for (int i = 0; i < tempSize; i++) {
            average += temperatureArray[i];
        }
        return average / tempSize;
    }

    public double deviation() {
        isEmpty();
        double avg = average();
        double sum = 0.0;
        for (int i = 0; i < tempSize; i++) {
            sum += (temperatureArray[i] - avg) * (temperatureArray[i] - avg);
        }
        return Math.sqrt(sum / (tempSize - 1));
    }

    public double min() {
        isEmpty();
        double min = temperatureArray[0];
        for (int i = 1; i < tempSize; i++) {
            if (min > temperatureArray[i]) {
                min = temperatureArray[i];
            }
        }
        return min;
    }

    public double max() {
        isEmpty();
        double max = temperatureArray[0];
        for (int i = 1; i < tempSize; i++) {
            if (max < temperatureArray[i]) {
                max = temperatureArray[i];
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0.0);
    }

    public double findTempClosestToValue(double tempValue) {
        isEmpty();
        double minDifference = Math.abs(temperatureArray[0] - tempValue);
        double closestValue = temperatureArray[0];
        final double MIN_FLOAT_DIFF = 0.001;
        for (int i = 1; i < tempSize; i++) {
            if (minDifference > Math.abs(temperatureArray[i] - tempValue)) {
                minDifference = Math.abs(temperatureArray[i] - tempValue);
                closestValue = temperatureArray[i];
            } else if (Math.abs(minDifference - Math.abs(
                    temperatureArray[i] - tempValue)) < MIN_FLOAT_DIFF) {
                closestValue = Math.max(closestValue, temperatureArray[i]);
            }
        }
        return closestValue;
    }

    private double[] findTemps(double tempValue, boolean isGreater) {
        TemperatureSeriesAnalysis temps = new TemperatureSeriesAnalysis();
        if (isGreater) {
            for (int i = 0; i < tempSize; i++) {
                if (temperatureArray[i] > tempValue) {
                    temps.addTemps(temperatureArray[i]);
                }
            }
        } else {
            for (int i = 0; i < tempSize; i++) {
                if (temperatureArray[i] < tempValue) {
                    temps.addTemps(temperatureArray[i]);
                }
            }
        }
        double[] tempsArrayWithoutEmpty = new double[temps.tempSize];
        System.arraycopy(temps.temperatureArray, 0,
                tempsArrayWithoutEmpty, 0, temps.tempSize);
        return tempsArrayWithoutEmpty;
    }

    public double[] findTempsLessThen(double tempValue) {
        return findTemps(tempValue, false);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return findTemps(tempValue, true);
    }

    public TempSummaryStatistics summaryStatistics() {
        isEmpty();
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (int i = 0; i < temps.length; i++) {
            if (tempCapacity > tempSize) {
                temperatureArray[tempSize++] = temps[i];
            } else {
                tempCapacity *= 2;
                double[] newTemperatureArray = new double[tempCapacity];
                System.arraycopy(temperatureArray, 0,
                        newTemperatureArray, 0, tempSize);
                temperatureArray = newTemperatureArray;
                temperatureArray[tempSize++] = temps[i];
            }
        }
        return tempSize;
    }
}
