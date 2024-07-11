import PongComposer from '@/communication/outgoing/generic/PongComposer'
import IncomingMessage from '../IncomingMessage'
import Client from '@/Client.ts'

interface PingData {
  message: string;
}

export default class PingEvent implements IncomingMessage {
  parse(data: PingData): void {
    console.log(data.message)
    Client.Overlay.communicationManager.sendMessage(new PongComposer())
  }
}
