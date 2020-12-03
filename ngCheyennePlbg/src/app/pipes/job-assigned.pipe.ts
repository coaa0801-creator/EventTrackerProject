import { Job } from './../models/job';
import { Pipe, PipeTransform } from '@angular/core';
import { Employee } from '../models/employee';

@Pipe({
  name: 'jobAssigned'
})
export class JobAssignedPipe implements PipeTransform {

  transform(staff: Employee[], assigned: String, job: Job): Employee[] {
const results = []

if(assigned == 'no'){
  staff.forEach(e => {
    if(job.staff.includes(e)){
      results.push(e);
    }
  });
}else if (assigned == 'yes'){
  staff.forEach(e => {
    if(!job.staff.includes(e)){
      results.push(e);
    }
  });



}
return results;

}
}
