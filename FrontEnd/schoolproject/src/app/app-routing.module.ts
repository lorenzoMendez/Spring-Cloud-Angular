import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudentComponent } from './components/student/student.component';
import { ExamComponent } from './components/exam/exam.component';
import { CourseComponent } from './components/course/course.component';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'alumnos' },
  { path: 'alumnos', component: StudentComponent },
  { path: 'examenes', component: ExamComponent },
  { path: 'cursos', component: CourseComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
