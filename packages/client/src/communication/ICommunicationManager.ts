import IncomingMessage from '@/communication/incoming/IncomingMessage.ts'
import { CommunicationType } from '@/communication/CommunicationType.ts'
import OutgoingMessage from '@/communication/outgoing/OutgoingMessage.ts'

export interface ICommunicationManager {
  readonly events: Map<string, IncomingMessage>
  mode: CommunicationType

  sendMessage(message: OutgoingMessage): void

  onMessage(message: string | MessageEvent): void

  onOpen(): void

  onClose(): void
}
