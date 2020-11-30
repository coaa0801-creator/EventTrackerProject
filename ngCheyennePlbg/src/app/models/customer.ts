import { Address } from './address';
import { Job } from './job';
export class Customer {

  id: number;
  firstName: String;
  lastName: String;
  email: String;
  address: Address;
  company: String;
  jobs: Job [];

  constructor(
    id?: number,
    firstName?: String,
    lastName?: String,
    email?: String,
    address?: Address,
    company?: String
    ){
      this.id= id;
            this.firstName= firstName;
            this.lastName= lastName;
            this.email= email;
            this.address= address;
            this.company= company;



  }






}
