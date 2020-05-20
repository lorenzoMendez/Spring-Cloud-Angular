import { Component, OnInit } from '@angular/core';
import { CourseService } from 'src/app/services/course.service';
import { CommonListingComponent } from '../common-listing.component';
import { Course } from 'src/app/models/course';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})

export class CourseComponent extends CommonListingComponent<Course, CourseService> implements OnInit {

  constructor( service: CourseService ) {
    super( service );
    this.tittle = "Listado de cursos"
    this.modelName = Course.name;
  }
}
