package ua.edu.ucu.tempseries;

public final class TempSummaryStatistics {
    double avgTemp;
    double devTemp;
    double minTemp;
    double maxTemp;

    public TempSummaryStatistics(double avg, double dev, double min, double max){
        this.avgTemp = avg;
        this.devTemp = dev;
        this.minTemp = min;
        this.maxTemp = max;
    }
}
