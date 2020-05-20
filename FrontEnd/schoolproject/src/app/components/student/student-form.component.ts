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

  private photo: File;

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
  
  public selectPhoto( event ): void {
    // Guarda la primera imagen, indice 0
    this.photo = event.target.files[ 0 ];
    console.log( this.photo );

    if( this.photo.type.indexOf( 'image' ) < 0 ) {
      this.photo = null;
      Swal.fire( "Error al seleccionar la foto", 'El archivo debe una imagen', 'error' );
    }
  }

  public save(): void {
    // Si no se selecciona la foto
    if( !this.photo ) {
      super.save(  );
    } else {
      this.service.saveWithPhoto( this.model, this.photo ).subscribe( student => {
        console.log( student );
        Swal.fire( 'Nuevo:', `'Alumno' ${ student.name } creado con éxito`, 'success' );
        this.router.navigate( [ this.redirect ] );
      }, err => {
        if( err.status === 400 ) {
          this.errors = err.error;
          console.log( this.errors );
        }
      } );
    }
  }

  public update(): void {
    // Si no se selecciona la foto
    if( !this.photo ) {
      super.save(  );
    } else {
      this.service.updateWithPhoto( this.model, this.photo ).subscribe( student => {
        console.log( student );
        Swal.fire( 'Modificado:', `'Alumno' ${ student.name } modificado con éxito`, 'success' );
        this.router.navigate( [ this.redirect ] );
      }, err => {
        if( err.status === 400 ) {
          this.errors = err.error;
          console.log( this.errors );
        }
      } );
    }
  }
  
}
