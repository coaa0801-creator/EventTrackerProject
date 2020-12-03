package com.skilldistillery.cheyenne.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String email;
	
	private String phone;
	
	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;

	@Column(name = "last_update")
	@UpdateTimestamp
	private LocalDateTime lastUpdate;
	
	private Boolean active = true;
	
	@OneToMany
	@JoinColumn(name="customer_id")
	private List<Address> addresses;
	
	private String company;
	
	@OneToMany(mappedBy="customer")
	@JsonIgnore
	private List<Job> jobs;

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public void addJob(Job job) {
		if (jobs == null) {
			jobs = new ArrayList<Job>();
		}
		if (!jobs.contains(job)) {
			jobs.add(job);
			job.addCustomer(this);
		}
	}

	public void removeJob(Job job) {
		if (jobs != null && jobs.contains(job)) {
			jobs.remove(job);
			job.removeCustomer(this);
		}
	}
//	public void addAddress(Address address) {
//		if (addresses == null) {
//			addresses = new ArrayList<Address>();
//		}
//		if (!addresses.contains(address)) {
//			addresses.add(address);
//			address.addCustomer(this);
//		}
//	}
//	
//	public void removeAddress(Address address) {
//		if (addresses != null && addresses.contains(address)) {
//			addresses.remove(address);
//			address.removeCustomer(this);
//		}
//	}

	public Customer() {
		super();
	}

	public Customer(int id, String firstName, String lastName, String email, LocalDateTime createDate,
			LocalDateTime lastUpdate, Boolean active, String company) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
		this.active = active;
		this.company = company;
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
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", createDate="
				+ createDate + ", lastUpdate=" + lastUpdate + ", active=" + active + ", company=" + company + "]";
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
