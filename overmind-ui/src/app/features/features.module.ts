import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmailComponent } from './email/email.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; 
import { NgxMatIntlTelInputComponent } from 'ngx-mat-intl-tel-input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [
    EmailComponent

  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    NgxMatIntlTelInputComponent,
  ], 
  exports:[
    EmailComponent
  ]
})
export class FeaturesModule { }
