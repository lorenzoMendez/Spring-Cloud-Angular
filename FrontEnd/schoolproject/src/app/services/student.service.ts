import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { EndpointService } from './endpoint.service';
import { Observable } from 'rxjs';
import { Student } from '../models/student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  
  private baseUrl: string;

  private headers: HttpHeaders = new HttpHeaders( { 'Content-Type': 'application/json' } );

  constructor( private http: HttpClient, private endPointService: EndpointService ) {
    this.baseUrl = this.endPointService.getEndPoint( 'api_microservices' );
  }
  
  public listingStudents() : Observable<Student[]> {
    return this.http.get<Student[]>( this.baseUrl + 'student/retrieve' );
    // return this.http.get<Student[]>( this.baseEndPoint ).pipe( map( students => students as Student[] ) );
  }

  public listingPageStudents( page: string, size: string ): Observable<any> {
    const params: HttpParams = new HttpParams()
          .set( 'page', page )
          .set( 'size', size );

    return this.http.get<any>( this.baseUrl + 'student/pagination', { params: params } );
  }

  public getStudent( studentId: number ) : Observable<Student> {
    return this.http.get<Student>( this.baseUrl + 'student/retrieve/' + studentId );
  }

  public saveStudent( student: Student ) : Observable<Student> {
    return this.http.post<Student>( this.baseUrl + 'student/save', student, { headers: this.headers } );
  }

  public updateStudent( student: Student ) : Observable<Student> {
    return this.http.put<Student>( this.baseUrl + 'student/update', student, { headers: this.headers } );
  }
  
  public deleteStudent( studentId: number ): Observable<void> {
    return this.http.delete<void>( this.baseUrl + 'student/' + studentId );
  }

}