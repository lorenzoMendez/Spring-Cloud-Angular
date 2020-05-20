import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';        // Module to work with http client
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentComponent } from './components/student/student.component';
import { CourseComponent } from './components/course/course.component';
import { ExamComponent } from './components/exam/exam.component';
import { LayoutModule } from './layout/layout.module';
import { StudentFormComponent } from './components/student/student-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule } from '@angular/material/paginator';
import { CourseFormComponent } from './components/course/course-form.component';

@NgModule({
  declarations: [
    AppComponent,
    StudentComponent,
    CourseComponent,
    ExamComponent,
    StudentFormComponent,
    CourseFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LayoutModule,                                        // Para trabajar con los modules
    HttpClientModule,                                     // Module to work with http client
    FormsModule, BrowserAnimationsModule,                 // Para implementar formularios en angular
    MatPaginatorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
