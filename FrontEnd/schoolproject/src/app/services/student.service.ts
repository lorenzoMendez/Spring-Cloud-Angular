import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from '../models/student';
import { CommonService } from './common.service';

import { END_POINT } from '../config/app';

@Injectable({
  providedIn: 'root'
})
export class StudentService extends CommonService<Student> {
  
  protected baseUrl: string = END_POINT[  "api_backend" ] + 'student';

  constructor( http: HttpClient ) {
    super( http );
  }

  public saveWithPhoto( student: Student, file: File ): Observable<Student> {
    
    const formData: FormData = new FormData();
    formData.append( 'file', file );
    formData.append( 'name', student.name );
    formData.append( 'lastName', student.lastName );
    formData.append( 'email', student.email );

    return this.http.post<Student>( this.baseUrl + '/save-with-photo', formData );
  }

  public updateWithPhoto( student: Student, file: File ): Observable<Student> {
    
    const formData: FormData = new FormData();
    formData.append( 'file', file );
    formData.append( 'id', student.id + '' );
    formData.append( 'name', student.name );
    formData.append( 'lastName', student.lastName );
    formData.append( 'email', student.email );
    
    return this.http.put<Student>( this.baseUrl + '/update-with-photo', formData );
  }
}