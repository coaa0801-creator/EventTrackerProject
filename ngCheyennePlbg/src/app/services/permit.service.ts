import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Permit } from '../models/permit';

@Injectable({
  providedIn: 'root'
})
export class PermitService {
  baseUrl = 'http://localhost:8083/';
  url = environment.baseUrl + 'api/permits'
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(private http: HttpClient) { }


  index(): Observable<Permit[]> {
    this.url = environment.baseUrl + 'api/permits'
    return this.http.get<Permit[]>(this.url + '?sorted=true')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  create(data: Permit) {
    this.url = environment.baseUrl + 'api/permits'
  return this.http.post<any>(this.url, data, this.httpOptions)  .pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError('Error getting Permit list');
    })
  );
}
}
