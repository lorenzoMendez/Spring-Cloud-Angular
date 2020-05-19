import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { EndpointService } from './endpoint.service';
import { Observable } from 'rxjs';
import { Student } from '../models/student';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class StudentService extends CommonService<Student> {
  
  protected baseUrl: string = ( new EndpointService() ).getEndPoint( "api_microservices" ) + 'student';

  constructor( http: HttpClient ) {
    super( http );
  }
}