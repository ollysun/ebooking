import { Injectable } from '@angular/core'
import { Action } from '@ngrx/store'
import { Book } from './../model/book'

export enum BookActionTypes {
    ADD_BOOKS = 'ADD_BOOKS',
    REMOVE_BOOK = 'REMOVE_BOOK',
    LIST_BOOK = 'LIST_BOOK'
}

export class AddBooks implements Action {
    readonly type = BookActionTypes.ADD_BOOKS

    constructor(public payload: Book) {}
}

export class RemoveBook implements Action {
    readonly type = BookActionTypes.REMOVE_BOOK

    constructor(public id: number) {}
}

export class ListBook implements Action {
    readonly type = BookActionTypes.LIST_BOOK

    constructor(public payload: any) {
    }
}


export type BookAction = AddBooks | RemoveBook | ListBook