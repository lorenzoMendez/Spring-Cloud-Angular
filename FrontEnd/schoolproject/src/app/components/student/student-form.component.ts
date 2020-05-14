import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.css']
})
export class StudentFormComponent implements OnInit {

  public tittle: string = "Alta estudiantes";

  public student: Student = new Student();

  public errors: any;

  constructor( 
    private studentService: StudentService, 
    private router: Router,
    private route: ActivatedRoute ) 
  { }

  ngOnInit(): void {

    this.route.queryParams.subscribe( params => {
      const id: number = +params[ "studentId" ];
      if( id ) {
        this.studentService.getStudent( id ).subscribe( student => this.student = student );
      }
    } );
  
  }

  public save(): void {
    this.studentService.saveStudent( this.student ).subscribe( student => {
      console.log( student );
      alert( `Alumno ${ student.name } creado con éxito` );
      this.router.navigate( ['/alumnos'] );
    }, err => {
      if( err.status === 400 ) {
        this.errors = err.error;
        console.log( this.errors );
      }
    } );
  }

  public edit(): void {
    this.studentService.updateStudent( this.student ).subscribe( student => {
      console.log( student );
      alert( `Alumno ${ student.name } actualizado con éxito` );
      this.router.navigate( ['/alumnos'] );
    }, err => {
      if( err.status === 400 ) {
        this.errors = err.error;
        console.log( this.errors ) ;
      }
    } );
  }
}
