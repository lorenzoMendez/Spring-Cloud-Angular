import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2'
import { CommonFormComponent } from '../common-form.component';

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.css']
})
export class StudentFormComponent extends CommonFormComponent<Student, StudentService> implements OnInit {

  constructor(
    service: StudentService,
    router: Router,
    route: ActivatedRoute) {
    super(service, router, route);
    this.tittle = "Alta estudiantes";
    this.model = new Student();
    this.redirect = '/alumnos';
    this.modelName = Student.name;
  }
}
