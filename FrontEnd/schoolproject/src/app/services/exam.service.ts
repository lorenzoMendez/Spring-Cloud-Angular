import { Injectable } from '@angular/core';
import { CommonService } from './common.service';
import { EndpointService } from './endpoint.service';
import { Exam } from '../models/exam';

@Injectable({
  providedIn: 'root'
})
export class ExamService extends CommonService<Exam> {
  
  protected baseUrl: string = ( new EndpointService() ).getEndPoint( "api_microservices" ) + 'exam';

}