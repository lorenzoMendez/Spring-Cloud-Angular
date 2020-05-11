import { Question } from './question';
import { Subject } from './subject';

export class Exam {
    examId: number;
    description: string;
    activeId: number;
    createDate: string;
    modifDate: string;
    questions: Question[] = [];
    subject: Subject;
    answerStatus: boolean;
}
