import IncomingMessage from '@/communication/incoming/IncomingMessage.ts'
import PingEvent from '@/communication/incoming/generic/PingEvent.ts'

export const INCOMING_MESSAGES: Map<string, IncomingMessage> = new Map()

INCOMING_MESSAGES.set('ping', new PingEvent())
