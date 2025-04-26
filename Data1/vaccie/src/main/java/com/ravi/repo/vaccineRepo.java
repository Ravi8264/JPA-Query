package com.ravi.repo;

import com.ravi.model.Vaccine;
import com.ravi.view.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface vaccineRepo extends JpaRepository<Vaccine, Integer> {
	// Basic queries
	List<Vaccine> findByVaccineName(String name);

	List<Vaccine> findByCostGreaterThan(Double cost);

	List<Vaccine> findByVaccineCompanyAndCost(String vaccineCompany, Double cost);

	// Complex queries
	List<Vaccine> findByVaccineNameContaining(String name);

	List<Vaccine> findByCostBetween(Double minCost, Double maxCost);

	List<Vaccine> findByVaccineCompanyIn(List<String> vaccineCompanies);

	List<Vaccine> findByVaccineNameIgnoreCase(String name);

	// Custom JPQL queries
	@Query("SELECT v FROM Vaccine v WHERE v.cost < :cost AND v.vaccineCompany = :vaccineCompany")
	List<Vaccine> findCheapVaccinesByManufacturer(@Param("cost") Double cost,
			@Param("vaccineCompany") String vaccineCompany);

	@Query("SELECT v FROM Vaccine v WHERE v.vaccineName LIKE CONCAT('%', :name, '%')")
	List<Vaccine> searchVaccinesByName(@Param("name") String name);

	@Query("SELECT v FROM Vaccine v WHERE v.cost BETWEEN :minCost AND :maxCost ORDER BY v.cost DESC")
	List<Vaccine> findVaccinesInPriceRange(@Param("minCost") Double minCost, @Param("maxCost") Double maxCost);

	// Native SQL query
	@Query(value = "SELECT * FROM vaccines WHERE cost < :cost AND vaccine_company = :vaccineCompany", nativeQuery = true)
	List<Vaccine> findCheapVaccinesByManufacturerNative(@Param("cost") Double cost,
			@Param("vaccineCompany") String vaccineCompany);

	// Projection query
	@Query("SELECT v.vaccineName as vaccineName, v.cost as cost FROM Vaccine v")
	List<ResultView> findVaccineNamesAndCosts();

	// Projection query for cost less than
	@Query("SELECT v.vaccineName as vaccineName, v.cost as cost FROM Vaccine v WHERE v.cost < :cost")
	List<ResultView> findByCostLessThan(@Param("cost") Double cost);

	// Cost Analysis View
	@Query(value = "SELECT " +
			"CASE " +
			"WHEN v.cost < 100 THEN 'Low' " +
			"WHEN v.cost < 500 THEN 'Medium' " +
			"ELSE 'High' END as priceRange, " +
			"COUNT(v) as vaccineCount, " +
			"COUNT(v) * 100.0 / (SELECT COUNT(v2) FROM Vaccine v2) as percentageOfTotal, " +
			"STRING_AGG(v.vaccineName, ', ') as vaccineNames " +
			"FROM Vaccine v " +
			"GROUP BY priceRange", nativeQuery = true)
	List<CostAnalysisView> getCostAnalysis();

	// Manufacturer Summary View
	@Query("SELECT " +
			"v.vaccineCompany as manufacturer, " +
			"COUNT(v) as vaccineCount, " +
			"SUM(v.cost) as totalInvestment " +
			"FROM Vaccine v " +
			"GROUP BY v.vaccineCompany")
	List<ManufacturerSummaryView> getManufacturerSummary();

	// Price Comparison View
	@Query("SELECT " +
			"v.vaccineName as vaccineName, " +
			"v.vaccineCompany as manufacturer, " +
			"v.cost as cost, " +
			"m.avgCost as averageMarketPrice, " +
			"v.cost - m.avgCost as priceDifference " +
			"FROM Vaccine v, " +
			"(SELECT v2.vaccineCompany as man, AVG(v2.cost) as avgCost FROM Vaccine v2 GROUP BY v2.vaccineCompany) m " +
			"WHERE v.vaccineCompany = m.man")
	List<PriceComparisonView> getPriceComparison();

	// Vaccine Search View
	@Query("SELECT " +
			"v.vaccineName as vaccineName, " +
			"v.vaccineCompany as manufacturer, " +
			"v.cost as cost, " +
			":searchTerm as searchTerm " +
			"FROM Vaccine v " +
			"WHERE v.vaccineName LIKE CONCAT('%', :searchTerm, '%') OR v.vaccineCompany LIKE CONCAT('%', :searchTerm, '%')")
	List<VaccineSearchView> searchVaccines(@Param("searchTerm") String searchTerm);

	// Vaccine Stats View
	@Query("SELECT " +
			"v.vaccineCompany as manufacturer, " +
			"CAST(COUNT(v) AS long) as totalVaccines, " +
			"AVG(v.cost) as averageCost, " +
			"MIN(v.cost) as minCost, " +
			"MAX(v.cost) as maxCost " +
			"FROM Vaccine v " +
			"GROUP BY v.vaccineCompany")
	List<VaccineStatsView> getVaccineStats();

	// Manufacturer Price Range View
	@Query("SELECT " +
			"v.vaccineCompany as manufacturer, " +
			"MIN(v.cost) as minCost, " +
			"MAX(v.cost) as maxCost " +
			"FROM Vaccine v " +
			"GROUP BY v.vaccineCompany")
	List<ManufacturerPriceRangeView> getManufacturerPriceRanges();
}
