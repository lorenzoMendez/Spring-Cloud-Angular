import { Student } from './student';
import { Question } from './question';

export class Answer {
    answeId: number;
    student: Student;
    question: Question;
    response: string;
    createDate: string;   
}
