package com.skilldistillery.cheyenne.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String email;
	
	private Boolean active = true;

	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	private Integer wage;
	
	@ManyToMany(mappedBy="staff")
	@JsonIgnore
	private List<Department> jobTypes;
	
	
	@ManyToMany(mappedBy="staff")
	@JsonIgnore
	private List<Job> jobs;
	
	
	
	
	public void addJob(Job job) {
		if (jobs == null) {
			jobs = new ArrayList<Job>();
		}
		if (!jobs.contains(job)) {
			jobs.add(job);
			job.addEmployee(this);
		}
	}

	public void removeJob(Job job) {
		if (jobs != null && jobs.contains(job)) {
			jobs.remove(job);
			job.removeEmployee(this);
		}
	}
	
	
	public void addJobType(Department jobType) {
		if (jobTypes == null) {
			jobTypes = new ArrayList<Department>();
		}
		if (!jobTypes.contains(jobType)) {
			jobTypes.add(jobType);
			jobType.addEmployee(this);
		}
	}

	public void removeJobType(Department jobType) {
		if (jobTypes != null && jobTypes.contains(jobType)) {
			jobTypes.remove(jobType);
			jobType.removeEmployee(this);
		}
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", active=" + active + ", address=" + address + ", wage=" + wage + "]";
	}

	public Employee() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		Employee other = (Employee) obj;
		if (active != other.active)
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getWage() {
		return wage;
	}

	public void setWage(Integer wage) {
		this.wage = wage;
	}

	public List<Department> getJobTypes() {
		return jobTypes;
	}

	public void setJobTypes(List<Department> jobTypes) {
		this.jobTypes = jobTypes;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
}
