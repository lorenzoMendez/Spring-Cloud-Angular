import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudentComponent } from './components/student/student.component';
import { ExamComponent } from './components/exam/exam.component';
import { CourseComponent } from './components/course/course.component';
import { StudentFormComponent } from './components/student/student-form.component';


const ROUTES: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'alumnos' },
  { path: 'alumnos', component: StudentComponent },
  { path: 'alumnos/form', component: StudentFormComponent },
  { path: 'alumnos/editar', component: StudentFormComponent },
  { path: 'examenes', component: ExamComponent },
  { path: 'cursos', component: CourseComponent }
];

@NgModule({
  imports: [RouterModule.forRoot( ROUTES ) ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
