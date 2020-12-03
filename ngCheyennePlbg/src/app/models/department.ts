import { Employee } from './employee';
import { Job } from './job';
import { Part } from './part';

export class Department {

  id: number;
  name: String;
  jobs: Job [];
  parts: Part [];
  staff: Employee [];

  constructor(
    id?: number,
    name?: String,
    jobs?: Job [],
    parts?: Part [],
    staff?: Employee [],


  ){

    this.id= id;
      this.name= name;
      this.jobs= jobs;
     this.parts= parts;
  this.staff= staff;

  }

}
