import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AvatarComponent } from './components/avatar/avatar.component';



@NgModule({
  declarations: [
    AvatarComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    AvatarComponent
  ],
})
export class SharedModule { }
