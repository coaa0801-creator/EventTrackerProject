import { Job } from './../models/job';
import { Pipe, PipeTransform } from '@angular/core';
import { Employee } from '../models/employee';

@Pipe({
  name: 'jobAssigned'
})
export class JobAssignedPipe implements PipeTransform {

  transform(staff: Employee[], assigned: String, job: Job): Employee[] {
const results = []
console.log(assigned);
console.log(staff);
console.log(job);

if(assigned == 'no'){
  staff.forEach(e => {
    e.jobs.forEach(j => {
      if(j != job){
        results.push(e);
      }
    });
  });
}else if (assigned == 'yes'){
  staff.forEach(e => {
    if(!e.jobs.includes(job)){
      results.push(e);
    }
});


}
return results;

}
}
