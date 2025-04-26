package com.ravi.view;

import java.util.List;

public interface CostAnalysisView {
    String getPriceRange();

    Long getVaccineCount();

    Double getPercentageOfTotal();

    List<String> getVaccineNames();
}