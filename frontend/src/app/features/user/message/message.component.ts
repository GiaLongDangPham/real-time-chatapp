import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MessageContent, MessageType } from 'src/app/core/interfaces/message-content';
import { MessageRoom } from 'src/app/core/interfaces/message-room';
import { MessageRoomMember } from 'src/app/core/interfaces/message-room-member';
import { User } from 'src/app/core/interfaces/user';
import { MessageContentService } from 'src/app/core/services/message-content.service';
import { MessageRoomMemberService } from 'src/app/core/services/message-room-member.service';
import { MessageRoomService } from 'src/app/core/services/message-room.service';
import { UserService } from 'src/app/core/services/user.service';
@Component({
    selector: 'app-message',
    templateUrl: './message.component.html',
    styleUrls: ['./message.component.scss']
})
export class MessageComponent {
    /**
       * 1. connect /api/ws
       * 2. subscribe /topic/active
       * 3. send connect to others /app/user/connect
       */

    currentUser: User = {};
    activeUsersSubscription: any;
    isShowDialogChat: boolean = false;
    selectedMessageRoom: MessageRoom = {};
    messageToSend: MessageContent = {};

    constructor(
        public userService: UserService,
        private messageRoomService: MessageRoomService,
        private messageContentService: MessageContentService,
        private messageRoomMemberService: MessageRoomMemberService,
        private router: Router,

    ) { }


    ngOnInit() {
        this.currentUser = this.userService.getFromLocalStorage();

        this.userService.connect(this.currentUser);
        this.messageContentService.connect(this.currentUser);

        window.addEventListener('beforeunload', () => {
            this.userService.disconnect(this.currentUser);
        });

        this.subscribeMessages();
    }


    ngOnDestroy() {
        this.userService.disconnect(this.currentUser);
        this.messageContentService.disconnect();
    }

    chat(selectedUsers: User[]) {
        console.log(selectedUsers);
        this.isShowDialogChat = false;
        const usernames = selectedUsers.map(u => u.username).filter((u): u is string => u !== undefined);
        if (this.currentUser.username) usernames.push(this.currentUser.username);
        this.messageRoomService.findMessageRoomByMembers(usernames).subscribe({
            next: (foundMessageRoom: MessageRoom) => {
                console.log('foundMessageRoom', foundMessageRoom);
                // Check if the message room not exists
                if (!foundMessageRoom) {
                    if (!this.currentUser.username) return;
                    // Create a new message room
                    this.messageRoomService.createChatRoom(this.currentUser.username, usernames).subscribe({
                        next: (createdMessageRoom: MessageRoom) => {
                            console.log('createdMessageRoom', createdMessageRoom);
                        },
                        error: (error) => {
                            console.log(error);
                        }
                    });
                }
            },
            error: (error) => {
                console.log(error);
            }
        });
    }

    selectMessageRoom(room: MessageRoom) {
        console.log(room);

        if (this.selectedMessageRoom.id) {
            this.updateLastSeen(this.selectedMessageRoom.id, this.currentUser.username);
        }

        this.selectedMessageRoom = room;

        if (this.selectedMessageRoom.id) {
            this.updateLastSeen(this.selectedMessageRoom.id, this.currentUser.username);
        }

        this.getMessagesByRoomId();
    }

    getMessagesByRoomId() {
        if (!this.selectedMessageRoom.id) return;
        this.messageContentService.getMessagesByRoomId(this.selectedMessageRoom.id).subscribe({
            next: (messages: MessageContent[]) => {
                console.log(messages);
                this.selectedMessageRoom.messages = messages;
            },
            error: (error) => {
                console.log(error);
            }
        });
    }

    subscribeMessages() {
        this.messageContentService.subscribeMessagesObservable().subscribe({
            next: (messageContent: MessageContent) => {
                console.log('messageContent', messageContent);
                this.selectedMessageRoom.messages?.push(messageContent);
            },
            error: (error) => {
                console.log(error);
            }
        });
    }

    sendMessage() {
        this.messageToSend = {
            content: this.messageToSend.content,
            messageRoomId: this.selectedMessageRoom.id,
            sender: this.currentUser.username,
            messageType: MessageType.TEXT,
        }

        this.messageContentService.sendMessage(this.messageToSend);

        console.log('messageToSend', this.messageToSend);
        this.messageToSend = {};
    }

    logout() {
        this.userService.disconnect(this.currentUser);
        this.messageContentService.disconnect();
        this.userService.removeFromLocalStorage();
        this.router.navigate(['/login']);
    }

    updateLastSeen(roomId?: string, username?: string) {
        this.messageRoomMemberService.updateLastSeen(roomId, username).subscribe({
            next: (member: MessageRoomMember) => {
                this.selectedMessageRoom.unseenCount = 0;
            }, error: (error: any) => {
                console.log(error);
            }
        });
    }
}
