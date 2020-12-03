import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Part } from '../models/part';

@Injectable({
  providedIn: 'root'
})
export class PartService {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8083/';
  url = environment.baseUrl + 'api/parts'
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  index(): Observable<Part[]> {
    this.url = environment.baseUrl + 'api/parts'
    return this.http.get<Part[]>(this.url + '?sorted=true')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  create(data: Part) {
    this.url = environment.baseUrl + 'api/parts'
  return this.http.post<any>(this.url, data, this.httpOptions)  .pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError('Error getting Part list');
    })
  );
}
}
