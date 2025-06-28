import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MessageRoutingModule } from './message-routing.module';
import { MessageComponent } from './message.component';
import { ActiveUsersListComponent } from './active-users-list/active-users-list.component';
import { SharedModule } from '../../shared/shared.module';
import { SelectUsersDialogComponent } from './select-users-dialog/select-users-dialog.component';
import { PrimengModule } from 'src/app/primeng/primeng.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    MessageComponent,
    ActiveUsersListComponent,
    SelectUsersDialogComponent
  ],
  imports: [
    CommonModule,
    MessageRoutingModule,
    SharedModule,
    PrimengModule,
    FormsModule
  ]
})
export class MessageModule { }
