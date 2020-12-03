import { Pipe, PipeTransform } from '@angular/core';
import { Department } from '../models/department';
import { Part } from '../models/part';

@Pipe({
  name: 'getParts'
})
export class GetPartsPipe implements PipeTransform {

  transform(jobTypes: Department[]): Part [] {
  const results = [];

  jobTypes.forEach(jT => {
    jT.parts.forEach(p => {
      results.push(p);

    });

  });

    return results;
  }

}
