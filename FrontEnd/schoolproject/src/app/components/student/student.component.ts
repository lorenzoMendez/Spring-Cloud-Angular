import { Component, OnInit } from '@angular/core';
import { StudentService } from 'src/app/services/student.service';
import { Student } from 'src/app/models/student';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  tittle: string = "Listado de alumnos";
  students: Student[] = [];

  // Se puede inyectar dependencias de esta forma
  constructor( private studentServie: StudentService ) {
    this.tittle = studentServie.getEndPoint();
  }

  ngOnInit(): void {
    // Primero suscribirse al observable
    this.studentServie.listingStudents().subscribe( students => this.students = students );
  }

}
