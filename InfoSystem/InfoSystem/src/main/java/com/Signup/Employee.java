package com.Signup;

import java.util.Objects;
import javax.persistence.Table;

@Table(name = "employee")
public class Employee {

	String name;
	String company;
	int contactNo;
	String emailAddress;
	String productInterested;
	String city;
	String country;
	String remark;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String name, String company, int contactNo, String emailAddress, String productInterested,
			String city, String country, String remark) {
		super();
		this.name = name;
		this.company = company;
		this.contactNo = contactNo;
		this.emailAddress = emailAddress;
		this.productInterested = productInterested;
		this.city = city;
		this.country = country;
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", company=" + company + ", contactNo=" + contactNo + ", emailAddress="
				+ emailAddress + ", productInterested=" + productInterested + ", city=" + city + ", country=" + country
				+ ", remark=" + remark + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getContactNo() {
		return contactNo;
	}

	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getProductInterested() {
		return productInterested;
	}

	public void setProductInterested(String productInterested) {
		this.productInterested = productInterested;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}



