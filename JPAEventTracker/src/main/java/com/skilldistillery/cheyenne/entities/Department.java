package com.skilldistillery.cheyenne.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToMany(mappedBy="jobTypes")
	@JsonIgnore
	private List<Job> jobs;
	
	@OneToMany(mappedBy="jobType")
	private List<Part> parts;
	
	@ManyToMany
	@JoinTable(name="employee_type", joinColumns = @JoinColumn(name = "department_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
	@JsonIgnore
	private List<Employee> staff;
	
	
	
	public void addPart(Part r) {
		if (parts == null) {
			parts = new ArrayList<Part>();
		}
		if (!parts.contains(r)) {
			parts.add(r);
			r.addJobType(this);
		}
	}
	
	
	public void removePart(Part r) {
		if (parts != null && parts.contains(r)) {
			parts.remove(r);
			r.removeJobType(this);
		}
	}
	public void addEmployee(Employee e) {
		if (staff == null) {
			staff = new ArrayList<Employee>();
		}
		if (!staff.contains(e)) {
			staff.add(e);
			e.addJobType(this);
		}
	}

	public void removeEmployee(Employee jobType) {
		if (staff != null && staff.contains(jobType)) {
			staff.remove(jobType);
			jobType.removeJobType(this);
		}
	}
	
	
	public void addJob(Job job) {
		if (jobs == null) {
			jobs = new ArrayList<Job>();
		}
		if (!jobs.contains(job)) {
			jobs.add(job);
			job.addJobType(this);
		}
	}

	public void removeJob(Job job) {
		if (jobs != null && jobs.contains(job)) {
			jobs.remove(job);
			job.removeJobType(this);
		}
	}
	
	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	public List<Employee> getStaff() {
		return staff;
	}

	public void setStaff(List<Employee> staff) {
		this.staff = staff;
	}

	

	public Department() {
		super();
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Department other = (Department) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
}
