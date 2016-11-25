package edu.mum.farmer.app.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class BillingDetails {
	@Id
	@GeneratedValue
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	private Address address;

	@NotEmpty
	private String phoneNumber;

	@NotEmpty
//	@CreditCardNumber
	private String creditCardNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

}
