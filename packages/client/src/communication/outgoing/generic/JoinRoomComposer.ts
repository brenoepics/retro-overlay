import OutgoingMessage from '../OutgoingMessage';

interface JoinRoomData {
  roomId: number;
}

export default class JoinRoomComposer implements OutgoingMessage {
  header: string = "join_room";    
  data: JoinRoomData;
  
  constructor(roomId: number) {
      this.data = {
          "roomId": roomId
      };
  }
}