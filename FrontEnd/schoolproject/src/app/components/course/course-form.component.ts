import { Component, OnInit } from '@angular/core';
import { CommonFormComponent } from '../common-form.component';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.css']
})
export class CourseFormComponent extends CommonFormComponent<Course, CourseService> implements OnInit {

  constructor(
    service: CourseService, 
    router: Router,
    route: ActivatedRoute ) {
      super( service, router, route );
      this.tittle = "Alta cursos";
      this.model = new Course();
      this.redirect = '/cursos';
      this.modelName = Course.name;
    }
    
}
