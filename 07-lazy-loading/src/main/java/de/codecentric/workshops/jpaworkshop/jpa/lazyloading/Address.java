package de.codecentric.workshops.jpaworkshop.jpa.lazyloading;

import de.codecentric.workshops.jpaworkshop.jpa.lazyloading.zipcode.Zipcode;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String street;
	private String city;
	private Zipcode zip;

	public Address(String street, String city, Zipcode zipcode) {
		this.street = street;
		this.city = city;
		this.zip = zipcode;
	}

	public Address() {

	}

	public Zipcode getZip() {
		return zip;
	}

	public void setZip(Zipcode zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
