import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PasswordComponent } from './password.component';

@NgModule({
  imports: [
    CommonModule, FormsModule
  ],
  declarations: [PasswordComponent],
  exports: [PasswordComponent]
})
export class PasswordModule { }
