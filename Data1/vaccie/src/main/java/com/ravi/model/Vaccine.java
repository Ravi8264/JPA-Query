package com.ravi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vaccines")
@Data

@AllArgsConstructor
public class Vaccine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String vaccineName;
	private String vaccineCompany;
	private Double cost;

	// Constructor without id
	public Vaccine(String vaccineName, String vaccineCompany, Double cost) {
		this.vaccineName = vaccineName;
		this.vaccineCompany = vaccineCompany;
		this.cost = cost;
	}

	public Integer getId() {
		return id;
	}

	Vaccine() {

	}

	@Override
	public String toString() {
		return "Vaccine [id=" + id + ", vaccineName=" + vaccineName + ", vaccineCompany=" + vaccineCompany + ", cost="
				+ cost + "]";
	}
}
