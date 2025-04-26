package com.ravi;

import com.ravi.model.Vaccine;
import com.ravi.service.VaccineService;
import com.ravi.view.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DataJpaProject01Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext container = SpringApplication.run(DataJpaProject01Application.class, args);

		VaccineService service = container.getBean(VaccineService.class);

		// Adding sample vaccines
		System.out.println("\n=== Adding Sample Vaccines ===");
		service.add(new Vaccine("Covaxin", "Bharat Biotech", 1200.0));
		service.add(new Vaccine("Covishield", "AstraZeneca", 900.0));
		service.add(new Vaccine("Sputnik V", "Gamaleya Research Institute", 1000.0));
		service.add(new Vaccine("Pfizer", "Pfizer-BioNTech", 1800.0));
		service.add(new Vaccine("Moderna", "Moderna Inc.", 1700.0));
		service.add(new Vaccine("Sinovac", "Sinovac Biotech", 800.0));
		service.add(new Vaccine("Johnson & Johnson", "Janssen Pharmaceuticals", 1400.0));
		service.add(new Vaccine("Novavax", "Novavax Inc.", 1300.0));
		service.add(new Vaccine("ZyCoV-D", "Zydus Cadila", 1250.0));
		service.add(new Vaccine("EpiVacCorona", "Vector Institute", 950.0));

		// Testing Basic Queries
		System.out.println("\n=== Testing Basic Queries ===");
		System.out.println("\n1. Find by Vaccine Name (Covaxin):");
		service.getByVaccineName("Covaxin").forEach(System.out::println);

		System.out.println("\n2. Find by Cost Greater Than (1500):");
		service.getByCostGreaterThan(1500.0).forEach(System.out::println);

		System.out.println("\n3. Find by Vaccine Company and Cost (Pfizer-BioNTech, 1800):");
		service.getByManufacturerAndCost("Pfizer-BioNTech", 1800.0).forEach(System.out::println);

		// Testing Complex Queries
		System.out.println("\n=== Testing Complex Queries ===");
		System.out.println("\n4. Search Vaccines by Name (containing 'cov'):");
		service.searchVaccinesByName("cov").forEach(System.out::println);

		System.out.println("\n5. Find Vaccines in Price Range (1000-1500):");
		service.getVaccinesInPriceRange(1000.0, 1500.0).forEach(System.out::println);

		System.out.println("\n6. Find Vaccines by Multiple Companies:");
		service.getVaccinesByManufacturers(Arrays.asList("Pfizer-BioNTech", "Moderna Inc."))
				.forEach(System.out::println);

		System.out.println("\n7. Search Vaccines by Name (case insensitive):");
		service.searchVaccinesByNameIgnoreCase("pfizer").forEach(System.out::println);

		// Testing Custom Queries
		System.out.println("\n=== Testing Custom Queries ===");
		System.out.println("\n8. Find Cheap Vaccines by Company (cost < 1000, AstraZeneca):");
		service.getCheapVaccinesByManufacturer(1000.0, "AstraZeneca").forEach(System.out::println);

		System.out.println("\n9. Search Vaccines by Name (custom query):");
		service.searchVaccinesByNameCustom("vac").forEach(System.out::println);

		System.out.println("\n10. Find Vaccines in Price Range (custom query):");
		service.getVaccinesInPriceRangeCustom(800.0, 1200.0).forEach(System.out::println);

		// Testing Native Query
		System.out.println("\n=== Testing Native Query ===");
		System.out.println("\n11. Find Cheap Vaccines by Company (native query):");
		service.getCheapVaccinesByManufacturerNative(1500.0, "Pfizer-BioNTech").forEach(System.out::println);

		// Testing Projection Query
		System.out.println("\n=== Testing Projection Query ===");
		System.out.println("\n12. Get Vaccine Names and Costs:");
		service.getVaccineNamesAndCosts().forEach(view -> System.out.println("Name: " + view.getVaccineName()));

		// Testing Existing Methods
		System.out.println("\n=== Testing Existing Methods ===");
		System.out.println("\n13. Search by Cost Less Than (1000):");
		service.searchByCostLessThan(1000.0).forEach(view -> System.out.println("Name: " + view.getVaccineName()));

		System.out.println("\n14. Get All Vaccines:");
		service.getAll().forEach(System.out::println);

		// Testing Custom Views
		System.out.println("\n=== Testing Custom Views ===");

		// Cost Analysis View
		System.out.println("\n15. Cost Analysis View:");
		List<CostAnalysisView> costAnalysis = service.getCostAnalysis();
		costAnalysis.forEach(view -> {
			System.out.println("Price Range: " + view.getPriceRange());
			System.out.println("Vaccine Count: " + view.getVaccineCount());
			System.out.println("Percentage of Total: " + view.getPercentageOfTotal() + "%");
			System.out.println("Vaccine Names: " + view.getVaccineNames());
			System.out.println("-------------------");
		});

		// Manufacturer Summary View
		System.out.println("\n16. Company Summary View:");
		List<ManufacturerSummaryView> manufacturerSummary = service.getManufacturerSummary();
		manufacturerSummary.forEach(view -> {
			System.out.println("Company: " + view.getManufacturer());
			System.out.println("Vaccine Count: " + view.getVaccineCount());
			System.out.println("Total Investment: " + view.getTotalInvestment());
			System.out.println("-------------------");
		});

		// Price Comparison View
		System.out.println("\n17. Price Comparison View:");
		List<PriceComparisonView> priceComparison = service.getPriceComparison();
		priceComparison.forEach(view -> {
			System.out.println("Vaccine: " + view.getVaccineName());
			System.out.println("Company: " + view.getManufacturer());
			System.out.println("Cost: " + view.getCost());
			System.out.println("Average Market Price: " + view.getAverageMarketPrice());
			System.out.println("Price Difference: " + view.getPriceDifference());
			System.out.println("-------------------");
		});

		// Vaccine Search View
		System.out.println("\n18. Vaccine Search View (search term: 'cov'):");
		List<VaccineSearchView> searchResults = service.searchVaccines("cov");
		searchResults.forEach(view -> {
			System.out.println("Vaccine: " + view.getVaccineName());
			System.out.println("Company: " + view.getManufacturer());
			System.out.println("Cost: " + view.getCost());
			System.out.println("Search Term: " + view.getSearchTerm());
			System.out.println("-------------------");
		});

		// Vaccine Stats View
		System.out.println("\n19. Vaccine Stats View:");
		List<VaccineStatsView> vaccineStats = service.getVaccineStats();
		vaccineStats.forEach(view -> {
			System.out.println("Company: " + view.getManufacturer());
			System.out.println("Total Vaccines: " + view.getTotalVaccines());
			System.out.println("Average Cost: " + view.getAverageCost());
			System.out.println("Min Cost: " + view.getMinCost());
			System.out.println("Max Cost: " + view.getMaxCost());
			System.out.println("-------------------");
		});

		// Manufacturer Price Range View
		System.out.println("\n20. Company Price Range View:");
		List<ManufacturerPriceRangeView> priceRanges = service.getManufacturerPriceRanges();
		priceRanges.forEach(view -> {
			System.out.println("Company: " + view.getManufacturer());
			System.out.println("Min Cost: " + view.getMinCost());
			System.out.println("Max Cost: " + view.getMaxCost());
			System.out.println("-------------------");
		});
	}
}
