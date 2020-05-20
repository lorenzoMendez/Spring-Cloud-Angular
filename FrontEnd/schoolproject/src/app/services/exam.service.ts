import { Injectable } from '@angular/core';
import { CommonService } from './common.service';
import { Exam } from '../models/exam';
import { END_POINT } from '../config/app';

@Injectable({
  providedIn: 'root'
})

export class ExamService extends CommonService<Exam> {
  
  protected baseUrl: string = END_POINT[  "api_backend" ] + 'exam';

}