import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookListComponent } from './book-list/book-list.component';
import { BookDetailComponent } from './book-detail/book-detail.component';
import { CreateBookComponent } from './create-book/create-book.component';


const routes: Routes = [
  { path: '', redirectTo: '/books', pathMatch: 'full' },
  { path: 'books', component: BookListComponent },
  { path: 'book/:id', component: BookDetailComponent },
  { path: 'createBook', component: CreateBookComponent },
  {
    path: '**',
    redirectTo: '',
    pathMatch: 'full'
}
];

@NgModule({
    imports: [ RouterModule.forRoot(routes)    ],
    exports: [ RouterModule ]
})
export class AppRoutingModule { }
