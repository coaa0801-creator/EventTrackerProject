package com.skilldistillery.cheyenne.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Job {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

private String name;

private int active;

private int paid;

@Column(name = "create_date")
@CreationTimestamp
private LocalDateTime createDate;

@Column(name = "last_update")
@UpdateTimestamp
private LocalDateTime lastUpdate;

private Double estimate;

@ManyToOne
@JoinColumn(name="customer_id")
private Customer customer;

@OneToMany(mappedBy="job")
private List<Permit> permits;

@ManyToMany
@JoinTable(name="job_department", joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "department_id"))
private List<Department> jobTypes;

@OneToOne
@JoinColumn(name="address_id")
private Address address;


@ManyToMany
@JoinTable(name="job_employee", joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
private List<Employee> staff;

@OneToMany(mappedBy="job")
@JsonIgnore
private List<Part> parts;


public int getActive() {
	return active;
}

public void setActive(int active) {
	this.active = active;
}

public int getPaid() {
	return paid;
}

public void setPaid(int paid) {
	this.paid = paid;
}

public LocalDateTime getCreateDate() {
	return createDate;
}

public void setCreateDate(LocalDateTime createDate) {
	this.createDate = createDate;
}

public LocalDateTime getLastUpdate() {
	return lastUpdate;
}

public void setLastUpdate(LocalDateTime lastUpdate) {
	this.lastUpdate = lastUpdate;
}

public Double getEstimate() {
	return estimate;
}

public void setEstimate(Double estimate) {
	this.estimate = estimate;
}

public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}

public List<Permit> getPermits() {
	return permits;
}

public void setPermits(List<Permit> permits) {
	this.permits = permits;
}

public List<Department> getJobTypes() {
	return jobTypes;
}

public void setJobTypes(List<Department> jobTypes) {
	this.jobTypes = jobTypes;
}

public Address getAddress() {
	return address;
}

public void setAddress(Address address) {
	this.address = address;
}

public List<Employee> getStaff() {
	return staff;
}

public void setStaff(List<Employee> staff) {
	this.staff = staff;
}

public List<Part> getParts() {
	return parts;
}

public void setParts(List<Part> parts) {
	this.parts = parts;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

@Override
public String toString() {
	return "Job [id=" + id + ", name=" + name + ", active=" + active + ", paid=" + paid + ", createDate=" + createDate
			+ ", lastUpdate=" + lastUpdate + ", estimate=" + estimate + ", customer=" + customer + ", address="
			+ address + ", staff=" + staff + "]";
}

public Job(int id, String name) {
	super();
	this.id = id;
	this.name = name;
}

public Job() {
	super();
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Job other = (Job) obj;
	if (id != other.id)
		return false;
	return true;
}




}
