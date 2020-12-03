import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Job } from '../models/job';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Customer } from '../models/customer';
import { Address } from '../models/address';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  constructor(private http: HttpClient) { }
  baseUrl = 'http://localhost:8083/';
  url = environment.baseUrl + 'api/jobs';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  index(): Observable<Job[]> {
    this.url = environment.baseUrl + 'api/jobs';
    return this.http.get<Job[]>(this.url + '?sorted=true')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }
  create(data: Job) {
    this.url = environment.baseUrl + 'api/jobs';
  return this.http.post<any>(this.url, data, this.httpOptions)
  .pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError('Error getting Job list');
    })
  );
}

update(job: Job, a: Address, c: Customer){
  job.address = a;
  job.customer = c;

  this.url = environment.baseUrl + 'api/jobs/' + job.id;
  return this.http.put<any>(this.url, job, this.httpOptions)
  .pipe(
    catchError((err: any) => {
      console.log(err);
      location.reload();
      return throwError('Error Updating Todo');
    })
    );

}



  destroy(data: number) {
    this.url = environment.baseUrl + 'api/jobs/' + data;
  return this.http.delete<any>(this.url, this.httpOptions)
  .pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError('Error getting Job list');
    })
  );
}

}
