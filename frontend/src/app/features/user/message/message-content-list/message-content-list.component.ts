import { Component, Input } from '@angular/core';
import { MessageContent } from 'src/app/core/interfaces/message-content';
import { MessageRoom } from 'src/app/core/interfaces/message-room';
import { User } from 'src/app/core/interfaces/user';

@Component({
  selector: 'app-message-content-list',
  templateUrl: './message-content-list.component.html',
  styleUrls: ['./message-content-list.component.scss']
})
export class MessageContentListComponent {
    @Input() selectedMessageRoom?: MessageRoom;
    @Input() currentUser?: User;
}
