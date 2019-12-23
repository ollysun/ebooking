import { Book } from "./book";

export class Response {
    public statusCode: number;
    public message: string;
    public data: Book;
    public validation:string
}