
import { Generic } from './generic';

export class Student implements Generic {
    id: number;
    name: string;
    lastName: string;
    email: string;
    statusId: number;
    createDate: string;
    modifDate: string;
    photoHashCode: number;
}