import { Component, OnInit } from '@angular/core';
import { StudentService } from 'src/app/services/student.service';
import { Student } from 'src/app/models/student';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  public tittle: string = "Listado de estudiantes";
  
  public students: Student[] = [];

  // Se puede inyectar dependencias de esta forma
  constructor( private studentService: StudentService ) { }

  ngOnInit(): void {
    // Primero suscribirse al observable
    this.studentService.listingStudents().subscribe( students => this.students = students );
  }

  public delete( student: Student ): void {
    if( confirm( `Esta seguro que desea eliminar a ${student.name}?` ) ) {
      this.studentService.deleteStudent( student.studentId ).subscribe( () => {
        this.students = this.students.filter( s => s !== student );
        alert( `Alumno ${ student.name } eliminado con exito.` );
      } );      
    }
  }

}
