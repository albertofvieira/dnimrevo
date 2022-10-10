import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  constructor(private http: HttpClient) { }

  public sendEmail(data: any): Observable<any>{
    return this.http.post<any>('http://localhost:8080/api/v1/email/send', data);
  }
}
