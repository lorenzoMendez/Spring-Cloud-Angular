import { Injectable } from '@angular/core';
import { Course } from '../models/course';
import { CommonService } from './common.service';
import { END_POINT } from '../config/app';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CourseService extends CommonService<Course> {
  
  protected baseUrl: string = END_POINT[  "api_backend" ] + 'course';

  constructor( http: HttpClient ) {
    super( http );
  }

}