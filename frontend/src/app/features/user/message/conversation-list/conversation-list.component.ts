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
    messageRooms: MessageRoom[] = [];

    constructor(
        private messageRoomService: MessageRoomService,
    ) { }

    ngOnInit() {
        this.findMessageRoomAtLeastOneContent();
    }

    findMessageRoomAtLeastOneContent() {
        if (!this.currentUser.username) return;
        // Find message rooms with at least one content for the current user
        this.messageRoomService.findMessageRoomAtLeastOneContent(this.currentUser.username).subscribe({
            next: (rooms: MessageRoom[]) => {
                console.log('rooms', rooms);
                this.messageRooms = rooms;
            }, error: (error) => {
                console.log(error);
            }
        });
    }
}
