import { Injectable } from '@angular/core';
import { Course } from '../models/course';
import { CommonService } from './common.service';
import { EndpointService } from './endpoint.service';

@Injectable({
  providedIn: 'root'
})
export class CourseService extends CommonService<Course> {
  
  protected baseUrl: string = ( new EndpointService() ).getEndPoint( "api_microservices" ) + 'course';

}