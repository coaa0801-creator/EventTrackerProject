package com.skilldistillery.cheyenne.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Part {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

private String name;

private Double price;


@ManyToOne
@JoinColumn(name="job_id")
private Job job;

private String condition;

private int available;

@Column(name="ship_time")
private Integer shipTime;

@ManyToOne
@JoinColumn(name="department_id")
private Department jobType;

@Column(name = "create_date")
@CreationTimestamp
private LocalDateTime createDate;

@Column(name = "last_update")
@UpdateTimestamp
private LocalDateTime lastUpdate;


public Part() {
	super();
}

@Override
public String toString() {
	return "Part [id=" + id + ", name=" + name + ", price=" + price + ", job=" + job + ", condition=" + condition
			+ ", available=" + available + ", shipTime=" + shipTime + ", jobType=" + jobType + ", createDate="
			+ createDate + ", lastUpdate=" + lastUpdate + "]";
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
	Part other = (Part) obj;
	if (id != other.id)
		return false;
	return true;
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

public Double getPrice() {
	return price;
}

public void setPrice(Double price) {
	this.price = price;
}

public Job getJob() {
	return job;
}

public void setJob(Job job) {
	this.job = job;
}

public String getCondition() {
	return condition;
}

public void setCondition(String condition) {
	this.condition = condition;
}

public int getAvailable() {
	return available;
}

public void setAvailable(int available) {
	this.available = available;
}

public Integer getShipTime() {
	return shipTime;
}

public void setShipTime(Integer shipTime) {
	this.shipTime = shipTime;
}

public Department getJobType() {
	return jobType;
}

public void setJobType(Department jobType) {
	this.jobType = jobType;
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

}
