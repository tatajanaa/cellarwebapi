package data_access;

import model.Threshold;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThresholdsRepo implements IThresholdAdapter {

    private Connection connection;
    Statement statement = null;
    List<Threshold> thresholdList = null;
    PreparedStatement stmt = null;

    public ThresholdsRepo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void setCO2Thresholds(double minValue, double maxValue) {

        try {
            stmt = connection.prepareStatement(
                    " update dbo.Thresholds" +
                            "  set minValue=" + minValue + ", maxValue=" + maxValue +
                            "  where SensorType='CO2';");


            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setHumidityThresholds(double minValue, double maxValue) {

        try {
            stmt = connection.prepareStatement(
                    " update dbo.Thresholds" +
                            "  set minValue=" + minValue + ", maxValue=" + maxValue +
                            "  where SensorType='Humidity';");

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTemperatureThresholds(double minValue, double maxValue) {

        try {
            stmt = connection.prepareStatement(
                    " update dbo.Thresholds" +
                            "  set minValue=" + minValue + ", maxValue=" + maxValue +
                            "  where SensorType='Temperature';");

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Threshold> getThresholds() {
        thresholdList = new ArrayList<>();

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("use SmartCellarWarehouse_SEP4A19G2 " +
                    "  select * from sourceDB_SEP4A19G2.dbo.Thresholds;");

            while (resultSet.next()) {
                Threshold threshold = new Threshold();

                threshold.setSensorType(resultSet.getString(1));
                threshold.setMinValue(resultSet.getDouble(2));
                threshold.setMaxValue(resultSet.getDouble(3));
                thresholdList.add(threshold);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(thresholdList);
        return thresholdList;


    }

    @Override
    public void updateOutOfBoundsTable() {

        try {
            stmt = connection.prepareStatement(
                    " update sourceDB_SEP4A19G2.dbo.outOfBounds set notified='True';");

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<Threshold> getOutOfBoundsLastReading() {

        thresholdList = new ArrayList<>();
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("use sourceDB_SEP4A19G2 " +
                    " select* from sourceDB_SEP4A19G2.dbo.outOfBounds where " +
                    "  notified=0;");

            while (resultSet.next()) {
                Threshold threshold = new Threshold();
                threshold.setSensorType(resultSet.getString("SensorType"));
                threshold.setMinValue(resultSet.getDouble("value"));
                thresholdList.add(threshold);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(thresholdList);

        return thresholdList;

    }

}