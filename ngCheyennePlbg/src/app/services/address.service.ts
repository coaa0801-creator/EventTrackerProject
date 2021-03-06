import { environment } from './../../environments/environment';
import { Address } from './../models/address';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http: HttpClient) { }
  url = environment.baseUrl + 'api/addresses'
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  index(): Observable<Address[]> {
    this.url = environment.baseUrl + 'api/addresses'
    return this.http.get<Address[]>(this.url + '?sorted=true')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  create(data: Address) {
    this.url = environment.baseUrl + 'api/addresses'
  return this.http.post<any>(this.url, data, this.httpOptions)  .pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError('Error getting Address list');
    })
  );
}



}
