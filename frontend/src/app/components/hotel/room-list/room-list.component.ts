import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { GetRoomResponse } from '../../../services/room/room.service';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrl: './room-list.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class RoomListComponent {
  @Input() availableRooms: GetRoomResponse[] = [];
}
