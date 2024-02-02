import OutgoingMessage from '../OutgoingMessage';

interface PongData {
  message: string;
}

export default class PongComposer implements OutgoingMessage {
  header: string = "pong";    
  data: PongData;
  
  constructor() {
      this.data = {
          "message": ""
      };
  }
}