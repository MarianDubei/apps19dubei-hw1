package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TemperatureSeriesAnalysisTest {

    private double[] temperatureSeriesOneElement;
    private double[] temperatureSeriesEmpty;
    private double[] temperatureSeries1;
    private TemperatureSeriesAnalysis seriesAnalysisOneElement;
    private TemperatureSeriesAnalysis seriesAnalysisEmpty;
    private TemperatureSeriesAnalysis seriesAnalysis1;

    @Before
    public void init(){
        temperatureSeriesOneElement = new double[] {1.0};
        temperatureSeriesEmpty = new double[] {};
        temperatureSeries1 = new double[] {3.0, -5.0, 1.0, 5.0};
        seriesAnalysisOneElement = new TemperatureSeriesAnalysis(temperatureSeriesOneElement);
        seriesAnalysisEmpty = new TemperatureSeriesAnalysis(temperatureSeriesEmpty);
        seriesAnalysis1 = new TemperatureSeriesAnalysis(temperatureSeries1);
    }

    @Test
    public void testAverageWithOneElementArray() {
        double expResult = 1.0;
        double actualResult = seriesAnalysisOneElement.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        seriesAnalysisEmpty.average();
    }

    @Test
    public void testAverage() {
        double expResult = 1.0;
        double actualResult = seriesAnalysis1.average();
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        seriesAnalysisEmpty.deviation();
    }

    @Test
    public void testDeviation() {
        double expResult = 4.32;
        double actualResult = seriesAnalysis1.deviation();
        assertEquals(expResult, actualResult, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        seriesAnalysisEmpty.min();
    }

    @Test
    public void testMin() {
        double expResult = -5.0;
        double actualResult = seriesAnalysis1.min();
        assertEquals(expResult, actualResult, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray() {
        seriesAnalysisEmpty.max();
    }

    @Test
    public void testMax() {
        double expResult = 5.0;
        double actualResult = seriesAnalysis1.max();
        assertEquals(expResult, actualResult, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroWithEmptyArray() {
        seriesAnalysisEmpty.findTempClosestToZero();
    }

    @Test
    public void testFindTempClosestToZero() {
        double expResult = 1.0;
        double actualResult = seriesAnalysis1.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueWithEmptyArray() {
        seriesAnalysisEmpty.findTempClosestToZero();
    }

    @Test
    public void testFindTempClosestToValue() {
        double expResult = 1.0;
        double actualResult = seriesAnalysis1.findTempClosestToValue(0.5);
        assertEquals(expResult, actualResult, 0.001);
    }

    @Test
    public void testFindTempClosestToValueEqualDistances() {
        double expResult = 1.0;
        double actualResult = seriesAnalysis1.findTempClosestToValue(-2.0);
        assertEquals(expResult, actualResult, 0.001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] expResult = new double[] {-5.0};
        double[] actualResult = seriesAnalysis1.findTempsLessThen(0.0);
        assertArrayEquals(expResult, actualResult, 0.001);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] expResult = new double[] {3.0, 1.0, 5.0};
        double[] actualResult = seriesAnalysis1.findTempsGreaterThen(0.0);
        assertArrayEquals(expResult, actualResult, 0.001);
    }

    @Test
    public void testSummaryStatistics() {
        TempSummaryStatistics summary = seriesAnalysis1.summaryStatistics();
        double[] actualResult = (new double[] {summary.getAvgTemp(), summary.getDevTemp(), summary.getMinTemp(), summary.getMaxTemp()});
        double[] expResult = (new double[] {1.0, 4.32, -5.0, 5.0});
        assertArrayEquals(expResult, actualResult, 0.001);
    }
}
