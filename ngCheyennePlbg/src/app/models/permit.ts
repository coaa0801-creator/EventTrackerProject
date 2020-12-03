import { Job } from './job';

export class Permit {
  id: number;
  identifier: String;
  type: String;
  job: Job;

  constructor(
    id?: number,
    identifier?: String,
    type?: String,
    job?: Job


  ){
    this.id = id;
    this.identifier = identifier;
    this.type = type;
    this.job = job;


  }
}
