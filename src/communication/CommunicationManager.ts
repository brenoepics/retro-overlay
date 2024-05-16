import { CommunicationType } from './CommunicationType'

import IncomingMessage from './incoming/IncomingMessage'
import OutgoingMessage from './outgoing/OutgoingMessage'
import Logger from '@/utils/Logger'

import PingEvent from './incoming/generic/PingEvent'
import { useConnectionStore } from '@/stores/connection'
import ExternalInterface from '@/externalinterface/ExternalInterface'
import EventAlertEvent from './incoming/event-alert/EventAlertEvent'

declare global {
  interface Window {
    FlashExternalInterface: ExternalInterface
    openroom: (message: string) => void
  }
}

export default class CommunicationManager {
  private readonly _events: Map<string, IncomingMessage>
  private _mode?: CommunicationType

  constructor() {
    this._events = new Map<string, IncomingMessage>()
    this.registerMessages()
  }

  private registerMessages(): void {
    this._events.set('ping', new PingEvent())
    this._events.set('eventAlert', new EventAlertEvent());
  }

  public sendMessage(message: OutgoingMessage): void {
    if (!useConnectionStore().connected || !message) return

    if (this._mode === CommunicationType.IFrameExternalFlashInterface) {
      const frame: HTMLIFrameElement | null = document.getElementById(
        'nitro',
      ) as HTMLIFrameElement

      if (frame?.contentWindow) {
        frame.contentWindow.openroom(JSON.stringify(message))
      } else {
        window.openroom(JSON.stringify(message))
      }

      return
    }

    const swfObject: HTMLObjectElement | HTMLEmbedElement | null =
      document.querySelector('object, embed')

    if (swfObject) {
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      ;(swfObject as any).openroom(JSON.stringify(message))
    }
  }

  public onMessage(message: string | MessageEvent): void {
    if (!useConnectionStore().connected || !useConnectionStore().handshake) {
      this.onOpen()
      useConnectionStore().setHandshake(true)
      Logger.info('Handshake completed')
    }

    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    let json: { header: string; data: any }
    if (typeof message === 'string' || message instanceof String) {
      json = JSON.parse(message.replace(/&#47;/g, '/'))
    } else {
      json = JSON.parse(message.data)
    }

    const parser = this._events.get(json.header)
    if (parser) {
      parser.parse(json.data)
    } else Logger.info('No parser found for message: ' + json.header)
  }

  public onOpen(): void {
    useConnectionStore().setConnected(true)
    Logger.info('connected')
  }

  onClose() {
    useConnectionStore().setConnected(false)
    Logger.info('disconnected')
  }
  public get events(): Map<string, IncomingMessage> {
    return this._events
  }

  public get mode(): CommunicationType {
    return this._mode!
  }

  public set mode(type: CommunicationType) {
    this._mode = type
  }
}
