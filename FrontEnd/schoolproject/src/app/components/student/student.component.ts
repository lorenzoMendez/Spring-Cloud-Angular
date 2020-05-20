import { Component, OnInit } from '@angular/core';
import { StudentService } from 'src/app/services/student.service';
import { Student } from 'src/app/models/student';

import { CommonListingComponent } from '../common-listing.component';
import { END_POINT } from '../../config/app';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})

export class StudentComponent extends CommonListingComponent<Student, StudentService> implements OnInit {

  public baseUrl: string = END_POINT[  "api_backend" ] + 'student';
  
  // Se puede inyectar dependencias de esta forma
  constructor( studentService: StudentService ) {
    super( studentService );                        // Pasamos el constructor a la clase que hereda
    this.tittle = "Listado de estudiantes";
    this.modelName = Student.name;
  }
}
