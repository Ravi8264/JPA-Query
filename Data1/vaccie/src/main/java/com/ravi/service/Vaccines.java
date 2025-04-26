package com.ravi.service;

import com.ravi.model.Vaccine;
import com.ravi.view.*;

import java.util.List;

public interface Vaccines {

	List<ResultView> searchByCostLessThan(Double cost);

	void add(Vaccine vaccine);

	void delete(int id);

	List<Vaccine> getAll();

	// Basic query methods
	List<Vaccine> getByVaccineName(String name);

	List<Vaccine> getByCostGreaterThan(Double cost);

	List<Vaccine> getByManufacturerAndCost(String vaccineCompany, Double cost);

	// Complex query methods
	List<Vaccine> searchVaccinesByName(String name);

	List<Vaccine> getVaccinesInPriceRange(Double minCost, Double maxCost);

	List<Vaccine> getVaccinesByManufacturers(List<String> vaccineCompanies);

	List<Vaccine> searchVaccinesByNameIgnoreCase(String name);

	// Custom query methods
	List<Vaccine> getCheapVaccinesByManufacturer(Double cost, String vaccineCompany);

	List<Vaccine> searchVaccinesByNameCustom(String name);

	List<Vaccine> getVaccinesInPriceRangeCustom(Double minCost, Double maxCost);

	// Native query method
	List<Vaccine> getCheapVaccinesByManufacturerNative(Double cost, String vaccineCompany);

	// Projection query method
	List<ResultView> getVaccineNamesAndCosts();

	// Custom View Methods
	List<CostAnalysisView> getCostAnalysis();

	List<ManufacturerSummaryView> getManufacturerSummary();

	List<PriceComparisonView> getPriceComparison();

	List<VaccineSearchView> searchVaccines(String searchTerm);

	List<VaccineStatsView> getVaccineStats();

	List<ManufacturerPriceRangeView> getManufacturerPriceRanges();
}
