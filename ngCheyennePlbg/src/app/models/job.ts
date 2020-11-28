export class Job {

id: number;
name: String;
active: number;
paid: number;
estimate: number;


constructor(
  id?: number,
  name?: String,
  active?: number,
  paid?: number,
  estimate?: number,
){
  this.id = id;
  this.name= name;
  this.active= active;
  this.paid= paid;
  this.estimate= estimate;

}
}
