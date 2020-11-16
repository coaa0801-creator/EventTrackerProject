## ![Logo](http://skilldistillery.com/downloads/sd_logo.jpg) Aaron Cottrell
# EventTrackerProject
## Overview
Work planner and estimate creator for independent contractor. Allows for tracking of jobs, labor and parts. Expansion possible for inventory tracking, estimates and billing. Work in progress.

## Step by Step (Short Version)
1. User Login
2. Manage schedule and billing from dashboard
3. Search, View, add or update jobs.

## Walkthrough (Long Version)
The event tracker is designed to enable independent contractors the ability to enter and track their contracted jobs from start to finish. A manager/owner will be able to have a unique to them dashboard which allows them to load in their departments, employees, inventory and any customer data associated with the job. Currently build out is in the early phases of testing and has limited functionality to only the creation, recall and update of the main "Job" entities. While delete functionality is provided in the event a job is cancelled or removed, the most common way to remove a job from the contractors list will be to update the job to "inactive". This change will allow the contractor to recall jobs that were cancelled or unfulfilled. This allows for inventory reclamation or find losses that may have been incurred by the unfinished work. Testing can be verified by using the below paths. We look forward to expanding on this initial system setup to allow for additional functionality.

| Return Type         | Route                                 | Functionality                           |
| ------------------- | ------------------------------------- | --------------------------------------- |
| List< Job > 	  | GET api/jobs                    	| Gets all Jobs                     	|
| Job       	    | GET api/workouts/{id}            	    | Gets one Job by id                	|
| Job           	| PUT api/jobs/{id}               	| Update a Job                      	|
| Job           	| POST api/jobs                   	| Add a Job                         	|
| Void              	| Delete api/jobs/{id}            	| Delete/Deactivate a Job                      	|



## Struggles
The only major issue I encountered was JPA ignoring default values from the DB. Utilized some instructor and colleague help to solve the issue. Everything else came together very quickly, I just need to take it a step further with additional repositories and service interfaces to add additional search and recall mobility.

## Lessons Learned
Learned that JPA can be unreliable in its creation of objects with default values. Assigned the entities themselves the default value for the boolean fields to avoid issues upon create method call.

## New Course Material
1. Postman
2. RESTful API
3. Spring Boot JUnit testing
4. JPA
5. MYSQL WorkBench
