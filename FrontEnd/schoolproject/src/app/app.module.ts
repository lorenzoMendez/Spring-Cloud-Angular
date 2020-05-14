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

@NgModule({
  declarations: [
    AppComponent,
    StudentComponent,
    CourseComponent,
    ExamComponent,
    StudentFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LayoutModule,               // Para trabajar con los modules
    HttpClientModule,            // Module to work with http client
    FormsModule                 // Para implementar formularios en angular
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
