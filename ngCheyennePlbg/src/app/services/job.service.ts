import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Job } from '../models/job';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  constructor(private http: HttpClient) { }
  baseUrl = 'http://localhost:8083/';
  url = this.baseUrl + 'api/jobs';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  index(): Observable<Job[]> {
    return this.http.get<Job[]>(this.url + '?sorted=true')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }
  create(data: Job) {
    this.url = this.baseUrl + 'api/jobs';
  return this.http.post<any>(this.url, data, this.httpOptions)
  .pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError('Error getting Pokemon list');
    })
  );
}

}
