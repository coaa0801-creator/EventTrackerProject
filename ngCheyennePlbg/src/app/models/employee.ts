import { Address } from './address';
import { Department } from './department';
import { Job } from './job';

export class Employee {
id: number;
firstName: String;
lastName: String;
email: String;
active: Boolean;
address: Address;
wage: number;
jobTypes: Department [];
jobs: Job [];

constructor(
  id?: number,
  firstName?: String,
  lastName?: String,
  email?: String,
  active?: Boolean,
  address?: Address,
  wage?: number,
  jobTypes?: Department [],
  jobs?: Job []


){

  this.id= id;
  this.firstName= firstName;
  this.lastName= lastName;
  this.email= email;
  this.active= active;
  this.address= address;
  this.wage= wage;
  this.jobTypes= jobTypes;
  this.jobs= jobs;


}

}
