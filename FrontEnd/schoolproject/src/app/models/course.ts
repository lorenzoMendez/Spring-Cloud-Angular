import { Student } from './student';
import { Exam } from './exam';
import { Generic } from './generic';

export class Course implements Generic {
    id: number;
    name: string;
    createDate: string;
    modifDate: string;
    activeId: number;
    students: Student[] = [];
    exams: Exam[] = [];
}
