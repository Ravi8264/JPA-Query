package com.ravi.service;

import com.ravi.model.Vaccine;
import com.ravi.repo.vaccineRepo;
import com.ravi.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineService implements Vaccines {

	private vaccineRepo repo;

	@Autowired
	public void setRepo(vaccineRepo repo) {
		this.repo = repo;
	}

	@Override
	public List<ResultView> searchByCostLessThan(Double cost) {
		return repo.findByCostLessThan(cost);
	}

	@Override
	public void add(Vaccine vaccine) {
		repo.save(vaccine);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

	@Override
	public List<Vaccine> getAll() {
		return repo.findAll();
	}

	// Basic query methods
	@Override
	public List<Vaccine> getByVaccineName(String name) {
		return repo.findByVaccineName(name);
	}

	@Override
	public List<Vaccine> getByCostGreaterThan(Double cost) {
		return repo.findByCostGreaterThan(cost);
	}

	@Override
	public List<Vaccine> getByManufacturerAndCost(String vaccineCompany, Double cost) {
		return repo.findByVaccineCompanyAndCost(vaccineCompany, cost);
	}

	// Complex query methods
	@Override
	public List<Vaccine> searchVaccinesByName(String name) {
		return repo.findByVaccineNameContaining(name);
	}

	@Override
	public List<Vaccine> getVaccinesInPriceRange(Double minCost, Double maxCost) {
		return repo.findByCostBetween(minCost, maxCost);
	}

	@Override
	public List<Vaccine> getVaccinesByManufacturers(List<String> vaccineCompanies) {
		return repo.findByVaccineCompanyIn(vaccineCompanies);
	}

	@Override
	public List<Vaccine> searchVaccinesByNameIgnoreCase(String name) {
		return repo.findByVaccineNameIgnoreCase(name);
	}

	// Custom query methods
	@Override
	public List<Vaccine> getCheapVaccinesByManufacturer(Double cost, String vaccineCompany) {
		return repo.findCheapVaccinesByManufacturer(cost, vaccineCompany);
	}

	@Override
	public List<Vaccine> searchVaccinesByNameCustom(String name) {
		return repo.searchVaccinesByName(name);
	}

	@Override
	public List<Vaccine> getVaccinesInPriceRangeCustom(Double minCost, Double maxCost) {
		return repo.findVaccinesInPriceRange(minCost, maxCost);
	}
	@Override
	public List<Vaccine> getCheapVaccinesByManufacturerNative(Double cost, String vaccineCompany) {
		return repo.findCheapVaccinesByManufacturerNative(cost, vaccineCompany);
	}

	// Projection query method
	@Override
	public List<ResultView> getVaccineNamesAndCosts() {
		return repo.findVaccineNamesAndCosts();
	}

	// Custom View Methods
	public List<CostAnalysisView> getCostAnalysis() {
		return repo.getCostAnalysis();
	}

	public List<ManufacturerSummaryView> getManufacturerSummary() {
		return repo.getManufacturerSummary();
	}

	public List<PriceComparisonView> getPriceComparison() {
		return repo.getPriceComparison();
	}

	public List<VaccineSearchView> searchVaccines(String searchTerm) {
		return repo.searchVaccines(searchTerm);
	}

	public List<VaccineStatsView> getVaccineStats() {
		return repo.getVaccineStats();
	}

	public List<ManufacturerPriceRangeView> getManufacturerPriceRanges() {
		return repo.getManufacturerPriceRanges();
	}
}
