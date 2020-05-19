import { Question } from './question';
import { Subject } from './subject';
import { Generic } from './generic';

export class Exam implements Generic {
    id: number;
    name: string;
    activeId: number;
    createDate: string;
    modifDate: string;
    questions: Question[] = [];
    subject: Subject;
    answerStatus: boolean;
}
