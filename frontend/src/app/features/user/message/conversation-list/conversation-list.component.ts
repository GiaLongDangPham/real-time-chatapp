import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MessageRoom } from 'src/app/core/interfaces/message-room';
import { User } from 'src/app/core/interfaces/user';
import { MessageRoomService } from 'src/app/core/services/message-room.service';

@Component({
    selector: 'app-conversation-list',
    templateUrl: './conversation-list.component.html',
    styleUrls: ['./conversation-list.component.scss']
})
export class ConversationListComponent {

    @Input() currentUser: User = {};
    @Input() selectedMessageRoomId: string | undefined = '';
    @Output() selectRoom = new EventEmitter<MessageRoom>();
    @Input() messageRooms: MessageRoom[] = [];


}
