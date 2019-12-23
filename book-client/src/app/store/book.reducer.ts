import { Action } from '@ngrx/store'
import { Book } from './../model/book'
import { BookActionTypes, BookAction } from './../store/book.action'

   const initialState: Book = {
       id:1,
       title:null,
       author:null,
       published:new Date(),
       notes:null
   };

 
 
export function BookReducer(state: Book[] = [initialState], action: BookAction) {   
  
    switch (action.type) {

        case BookActionTypes.LIST_BOOK: 
          return  [...state,initialState];

        case BookActionTypes.ADD_BOOKS: 
            return [...state, action.payload];

        case BookActionTypes.REMOVE_BOOK: 
            return state.filter((el)=>el.id != action.id);

        default: 
            return state;
    }
}