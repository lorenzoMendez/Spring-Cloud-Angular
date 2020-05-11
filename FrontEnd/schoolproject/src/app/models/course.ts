import { Student } from './student';
import { Exam } from './exam';

export class Course {
    courseId: number;
    createDate: string;
    modifDate: string;
    activeId: number;
    students: Student[] = [];
    exams: Exam[] = [];
}
