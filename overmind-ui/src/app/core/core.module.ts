import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThemeService } from './services/theme.service';
import { EmailService } from './services/email.service';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [
    ThemeService,
    EmailService
  ]
})
export class CoreModule { }
