import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { EndpointService } from './endpoint.service';
import { Observable } from 'rxjs';
import { Student } from '../models/student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  
  private baseEndPoint: string;

  private headers: HttpHeaders = new HttpHeaders( { 'Content-Type': 'application/json' } );

  constructor( private http: HttpClient, private endPointService: EndpointService ) {
    this.baseEndPoint = this.endPointService.getEndPoints()[ 'api_microservices' ];
  }

  public getEndPoint(){
    return this.baseEndPoint;
  }
  
  public listingStudents() : Observable<Student[]> {
    return this.http.get<Student[]>( this.baseEndPoint + 'student/retrieve' );
    // return this.http.get<Student[]>( this.baseEndPoint ).pipe( map( students => students as Student[] ) );
  }

  public listingPageStudents( page: string, size: string ): Observable<any> {
    const params: HttpParams = new HttpParams()
          .set( 'page', page )
          .set( 'size', size );

    return this.http.get<any>( this.baseEndPoint + 'student/pagination', { params: params } );
  }

  public getStudent( studentId: number ) : Observable<Student> {
    return this.http.get<Student>( this.baseEndPoint + 'student/retrieve/' + studentId );
  }

  public saveStudent( student: Student ) : Observable<Student> {
    return this.http.post<Student>( this.baseEndPoint + 'student/save', student, { headers: this.headers } );
  }

  public updateStudent( student: Student ) : Observable<Student> {
    return this.http.put<Student>( this.baseEndPoint + 'student/update', student, { headers: this.headers } );
  }
  
  public deleteStudent( studentId: number ): Observable<void> {
    return this.http.delete<void>( this.baseEndPoint + 'student/' + studentId );
  }

}