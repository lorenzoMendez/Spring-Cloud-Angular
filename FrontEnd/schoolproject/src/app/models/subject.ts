export class Subject {
    subjectId: number;
    description: string;
    activeId: number;
    createDate: string;
    modifDate: string;
    father: Subject;
    sons: Subject[] = [];
}
