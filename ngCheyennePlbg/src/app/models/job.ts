import { Address } from './address';
import { Customer } from './customer';
export class Job {

id: number;
name: String;
active: number;
paid: number;
estimate: number;
customer: Customer;
address: Address;


constructor(
  id?: number,
  name?: String,
  active?: number,
  paid?: number,
  estimate?: number,
  customer?: Customer,
  address?: Address
){
  this.id = id;
  this.name= name;
  this.active= active;
  this.paid= paid;
  this.estimate= estimate;
  this.customer= customer;
  this.address= address;

}
}
