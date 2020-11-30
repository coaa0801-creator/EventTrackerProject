import { Customer } from './../models/customer';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }
  baseUrl = 'http://localhost:8083/';
  url = this.baseUrl + 'api/customers'
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  index(): Observable<Customer[]> {
    this.url = this.baseUrl + 'api/customers'
    return this.http.get<Customer[]>(this.url + '?sorted=true')
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
      );
    }
    create(data: Customer) {
      this.url = this.baseUrl + 'api/customers'
    return this.http.post<any>(this.url, data, this.httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error getting Pokemon list');
      })
    );
  }

}
