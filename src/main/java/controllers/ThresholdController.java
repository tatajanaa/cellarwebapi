package controllers;

import data_access.JDBC_connection.SourceDbConnection;
import data_access.ThresholdsRepo;

public class ThresholdController {

    ThresholdsRepo repo;

    public ThresholdController() {
        repo = new ThresholdsRepo(SourceDbConnection.getConnection());
    }

    public void setCO2Thresholds(double minValue, double maxValue){

        repo.setCO2Thresholds(minValue, maxValue);

   }

    public void setHumidityThresholds(double minValue, double maxValue){

        repo.setHumidityThresholds(minValue, maxValue);

    }

    public void setTemperatureThresholds(double minValue, double maxValue){

        repo.setTemperatureThresholds(minValue, maxValue);

    }

}