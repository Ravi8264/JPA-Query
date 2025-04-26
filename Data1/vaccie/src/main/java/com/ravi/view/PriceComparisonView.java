package com.ravi.view;

public interface PriceComparisonView {
    String getVaccineName();

    String getManufacturer();

    Double getCost();

    Double getAverageMarketPrice();

    Double getPriceDifference();
}