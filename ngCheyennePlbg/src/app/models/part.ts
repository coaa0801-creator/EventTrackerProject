import { Department } from './department';
import { Job } from './job';

export class Part {
id: number;
name: String;
price: number;
image: String;
job: Job;
condition: String;
available: Boolean;
shipTime: number;
jobType: Department;

constructor(
  id?: number,
  name?: String,
  price?: number,
  image?: String,
  job?: Job,
  condition?: String,
  available?: Boolean,
  shipTime?: number,
  jobType?: Department

){
  this.id= id    ;
  this.name=   name  ;
  this.price=   price  ;
  this.image=   image  ;
  this.job=   job  ;
  this.condition=  condition   ;
  this.available=    available ;
  this.shipTime= shipTime    ;
  this.jobType=    jobType ;

}

}
