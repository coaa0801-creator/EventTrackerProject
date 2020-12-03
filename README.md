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

#### *API Endpoints*

http://localhost:8083/

| Return Type         | Route                                 | Functionality                           |
| ------------------- | ------------------------------------- | --------------------------------------- |
| List< Job > 	  | GET api/jobs                    	| Gets all Jobs                     	|
| Job       	    | GET api/jobs/{id}            	    | Gets one Job by id                	|
| Job           	| PUT api/jobs/{id}               	| Update a Job                      	|
| Job           	| POST api/jobs                   	| Add a Job                         	|
| Void              	| Delete api/jobs/{id}            	| Delete/Deactivate a Job                      	|
| List< Customer > 	  | GET api/customers                    	| Gets all Customers                     	|
| Customer       	    | GET api/customers/{id}            	    | Gets one Customer by id                	|
| Customer           	| PUT api/customers/{id}               	| Update a Customer                      	|
| Customer           	| POST api/customers                   	| Add a Customer                         	|
| Void              	| Delete api/customers/{id}            	| Delete/Deactivate a Customer                      	|
| List< Part > 	  | GET api/parts                    	| Gets all Parts                     	|
| Part       	    | GET api/parts/{id}            	    | Gets one Part by id                	|
| Part           	| PUT api/parts/{id}               	| Update a Part                      	|
| Part           	| POST api/parts                   	| Add a Part                         	|
| Void              	| Delete api/parts/{id}            	| Delete/Deactivate a Part                      	|
| List< Department > 	  | GET api/departments                    	| Gets all Departments                     	|
| Department       	    | GET api/departments/{id}            	    | Gets one Department by id                	|
| Department           	| PUT api/departments/{id}               	| Update a Department                      	|
| Department           	| POST api/departments                   	| Add a Department                         	|
| Void              	| Delete api/departments/{id}            	| Delete/Deactivate a Department                      	|
| List< Address > 	  | GET api/addresses                    	| Gets all Addresses                     	|
| Address       	    | GET api/addresses/{id}            	    | Gets one Address by id                	|
| Address           	| PUT api/addresses/{id}               	| Update an Address                      	|
| Address           	| POST api/addresses                   	| Add an Address                         	|
| Void              	| Delete api/addresses/{id}            	| Delete/Deactivate an Address                      	|
| List< Permit > 	  | GET api/permits                    	| Gets all Permits                     	|
| Permit       	    | GET api/permits/{id}            	    | Gets one Permit by id                	|
| Permit           	| PUT api/permits/{id}               	| Update a Permit                      	|
| Permit           	| POST api/permits                   	| Add a Permit                         	|
| Void              	| Delete api/permits/{id}            	| Delete/Deactivate a Permit                      	|
| List< Employee > 	  | GET api/employees                    	| Gets all Employees                     	|
| Employee       	    | GET api/employees/{id}            	    | Gets one Employee by id                	|
| Employee           	| PUT api/employees/{id}               	| Update an Employee                      	|
| Employee           	| POST api/employees                   	| Add an Employee                         	|
| Void              	| Delete api/employees/{id}            	| Delete/Deactivate an Employee                      	|



## Struggles
The only major issue I encountered was JPA ignoring default values from the DB. Utilized some instructor and colleague help to solve the issue. Everything else came together very quickly, I just need to take it a step further with additional repositories and service interfaces to add additional search and recall mobility.

Multiple Entity simultaneous updates.

I really look forward to the continued work on this project. Going to be a little bit of a pet for a while as I continue to build it out.

## Lessons Learned
Learned that JPA can be unreliable in its creation of objects with default values. Assigned the entities themselves the default value for the boolean fields to avoid issues upon create method call.

## New Course Material
1. Postman
2. RESTful API
3. Spring Boot JUnit testing
4. JPA
5. MYSQL WorkBench
