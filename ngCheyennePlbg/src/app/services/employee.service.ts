import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Employee } from '../models/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  baseUrl = 'http://localhost:8083/';
  url = this.baseUrl + 'api/employees'
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(private http: HttpClient) { }


  index(): Observable<Employee[]> {
    this.url = this.baseUrl + 'api/employees'
    return this.http.get<Employee[]>(this.url + '?sorted=true')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  create(data: Employee) {
    this.url = this.baseUrl + 'api/employees'
  return this.http.post<any>(this.url, data, this.httpOptions)  .pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError('Error getting Employee list');
    })
  );
}
}
