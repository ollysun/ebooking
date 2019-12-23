import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CreateBookComponent } from './create-book/create-book.component';
import { BookDetailComponent } from './book-detail/book-detail.component';
import { BookListComponent } from './book-list/book-list.component';
import { MatCardModule, MatTableModule, MatDatepickerModule,
        MatInputModule, MatNativeDateModule, MatToolbarModule, MatButtonModule, MatDialogModule } from '@angular/material';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './/app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StoreModule } from '@ngrx/store';
import { BookReducer } from './store/book.reducer';
import { StoreDevtoolsModule } from '@ngrx/store-devtools'




@NgModule({
  declarations: [
    AppComponent,
    CreateBookComponent,
    BookDetailComponent,
    BookListComponent
    ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatCardModule,
    MatInputModule, 
    MatToolbarModule,
    MatTableModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    StoreModule.forRoot({
      bookReducer:BookReducer
    }),
    StoreDevtoolsModule.instrument({
      maxAge: 25, // Restrict extension to log-only mode
    }),
  ],
  exports: [CommonModule, MatToolbarModule, MatButtonModule, MatCardModule, MatInputModule,
     MatDialogModule, MatTableModule, MatDatepickerModule, MatNativeDateModule],
  providers: [ MatDatepickerModule ],
  bootstrap: [AppComponent]
})
export class AppModule { }
