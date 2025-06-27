import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MessageRoutingModule } from './message-routing.module';
import { MessageComponent } from './message.component';
import { ActiveUsersListComponent } from './active-users-list/active-users-list.component';
import { SharedModule } from '../../shared/shared.module';

@NgModule({
  declarations: [
    MessageComponent,
    ActiveUsersListComponent
  ],
  imports: [
    CommonModule,
    MessageRoutingModule,
    SharedModule
  ]
})
export class MessageModule { }
