import { OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2'
import { CommonService } from '../services/common.service';
import { Generic } from '../models/generic';

export abstract class CommonFormComponent<E extends Generic, S extends CommonService<E>> implements OnInit {

  public tittle: string;
  public model: E;
  public errors: any;
  // Pagina a la que redireccionaremos
  protected redirect: string;
  // Nombre del modelo, Alumno, Examen o Curso
  protected modelName: string;

  constructor(
    protected service: S, 
    protected router: Router,
    protected route: ActivatedRoute ) { }

  ngOnInit(): void {

    this.route.queryParams.subscribe( params => {
      // El signo + castea el string a un number
      const id: number = +params[ "id" ];
      if( id ) {
        this.service.get( id ).subscribe( model => this.model = model );
      }
    } );
  }

  public save(): void {
    this.service.save( this.model ).subscribe( model => {
      console.log( model );
      Swal.fire( 'Nuevo:', `${this.modelName} ${ model.name } creado con éxito`, "success" );
      this.router.navigate( [ this.redirect ] );
    }, err => {
      if( err.status === 400 ) {
        this.errors = err.error;
        console.log( this.errors );
      }
    } );
  }

  public edit(): void {
    this.service.update( this.model ).subscribe( model => {
      console.log( model );
      Swal.fire( 'Modificado: ',  `${this.modelName} ${ model.name } actualizado con éxito`, 'success' );
      this.router.navigate( [ this.redirect ] );
    }, err => {
      if( err.status === 400 ) {
        this.errors = err.error;
        console.log( this.errors ) ;
      }
    } );
  }
}
