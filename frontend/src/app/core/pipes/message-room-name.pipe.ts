import { Pipe, PipeTransform } from '@angular/core';
import { MessageRoom } from '../interfaces/message-room';
import { UserService } from '../services/user.service';
import { User } from '../interfaces/user';

@Pipe({
  name: 'messageRoomName'
})
export class MessageRoomNamePipe implements PipeTransform {

  currentUser: User = this.userService.getFromLocalStorage();


  constructor(
    private userService: UserService,
  ) {}

  transform(room?: MessageRoom): string | undefined {
    if(!room) return '';

    if(room.name) {
      return room.name;
    }
    else {
        const filteredMembers = room.members?.filter(m => {
            console.log('room.members =', room.members);
            return room.isGroup || m.username !== this.currentUser.username;
        });

        // Bước 2: Lấy danh sách tên người dùng
        const roomNames = filteredMembers?.map(u => u.username).join(', ');

        // Bước 3: In kết quả ra console
        return roomNames;
    }
  }

}

// duong
// an duong dung
// an duong dung


// last message
// room name


// 3 truong hop
// 1. nhom - room da co ten => hien ten
// 2. nhom - chua co ten => danh sach member phan tach boi dau , 
// 3. chi co 2 nguoi => hien thi ten cua nguoi con lai

// ====> string
