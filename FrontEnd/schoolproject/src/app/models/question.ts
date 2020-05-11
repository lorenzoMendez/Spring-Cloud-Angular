import { Exam } from './exam';

export class Question {
    questionId: number;
    description: string;
    createDate: string;
    modifDate: string;
    exam: Exam;
}
