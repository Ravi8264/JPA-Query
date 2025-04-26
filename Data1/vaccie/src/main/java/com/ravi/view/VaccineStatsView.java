package com.ravi.view;

public interface VaccineStatsView {
    String getManufacturer();

    Long getTotalVaccines();

    Double getAverageCost();

    Double getMinCost();

    Double getMaxCost();
}