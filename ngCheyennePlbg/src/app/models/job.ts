import { Address } from './address';
import { Customer } from './customer';
import { Department } from './department';
import { Employee } from './employee';
import { Part } from './part';
import { Permit } from './permit';
export class Job {

id: number;
name: String;
active: number;
paid: number;
estimate: number;
customer: Customer;
address: Address;
permits: Permit[];
jobTypes: Department[];
parts: Part[];
staff: Employee[];


constructor(
  id?: number,
  name?: String,
  active?: number,
  paid?: number,
  estimate?: number,
  customer?: Customer,
  address?: Address,
  permits?: Permit[],
jobTypes?: Department[],
parts?: Part[],
staff?: Employee[]
){
  this.id = id;
  this.name= name;
  this.active= active;
  this.paid= paid;
  this.estimate= estimate;
  this.customer= customer;
  this.address= address;
  this.permits= permits;
this.jobTypes= jobTypes;
this.parts= parts;
this.staff= staff;

}
}
