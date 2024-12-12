package com.bank.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer empId;
	private String name;
	private Long mobileNo;
	private double salary;
	private Boolean isMarried;
	private LocalDate dob;
	private String joinedAt;
	public Customer() {
		super();
	}
	public Customer(Integer empId, String name, Long mobileNo, double salary, Boolean isMarried, LocalDate dob,
			String joinedAt) {
		super();
		this.empId = empId;
		this.name = name;
		this.mobileNo = mobileNo;
		this.salary = salary;
		this.isMarried = isMarried;
		this.dob = dob;
		this.joinedAt = joinedAt;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Boolean getIsMarried() {
		return isMarried;
	}
	public void setIsMarried(Boolean isMarried) {
		this.isMarried = isMarried;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getJoinedAt() {
		return joinedAt;
	}
	public void setJoinedAt(String joinedAt) {
		this.joinedAt = joinedAt;
	}
	@Override
	public String toString() {
		return "Customer [empId=" + empId + ", name=" + name + ", mobileNo=" + mobileNo + ", salary=" + salary
				+ ", isMarried=" + isMarried + ", dob=" + dob + ", joinedAt=" + joinedAt + "]";
	}
}
