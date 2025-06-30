import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MessageRoutingModule } from './message-routing.module';
import { MessageComponent } from './message.component';
import { ActiveUsersListComponent } from './active-users-list/active-users-list.component';
import { SharedModule } from '../../shared/shared.module';
import { SelectUsersDialogComponent } from './select-users-dialog/select-users-dialog.component';
import { PrimengModule } from 'src/app/primeng/primeng.module';
import { FormsModule } from '@angular/forms';
import { ConversationListComponent } from './conversation-list/conversation-list.component';
import { ConversationComponent } from './conversation/conversation.component';
import { CoreModule } from 'src/app/core/core.module';
import { MessageContentListComponent } from './message-content-list/message-content-list.component';
import { MessageContentComponent } from './message-content/message-content.component';

@NgModule({
  declarations: [
    MessageComponent,
    ActiveUsersListComponent,
    SelectUsersDialogComponent,
    ConversationListComponent,
    ConversationComponent,
    MessageContentListComponent,
    MessageContentComponent
  ],
  imports: [
    CommonModule,
    MessageRoutingModule,
    SharedModule,
    PrimengModule,
    FormsModule,
    CoreModule
  ]
})
export class MessageModule { }
