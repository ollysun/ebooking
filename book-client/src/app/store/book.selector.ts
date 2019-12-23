import { createSelector } from '@ngrx/store';

import { AppState } from '../app.state';
import { Book } from './../model/book'

export function getState(state: AppState): AppState {
    return state;
}

export function fetchProducts(state: AppState): Book[] {
    return state.book;
}

export const getProducts = createSelector(getState, fetchProducts);