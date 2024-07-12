import { CommunicationType } from './CommunicationType'

import IncomingMessage from './incoming/IncomingMessage'
import OutgoingMessage from './outgoing/OutgoingMessage'
import Logger from '@/utils/Logger'

import { useConnectionStore } from '@/stores/connection'
import { INCOMING_MESSAGES } from '@/communication/PluginMessageRegistrar.ts'
import { ICommunicationManager } from '@/communication/ICommunicationManager.ts'
import RequestSessionDataComposer from '@/communication/outgoing/session/RequestSessionDataComposer.ts'

export default class CommunicationManager implements ICommunicationManager {
  private readonly _events: Map<string, IncomingMessage>

  constructor() {
    this._events = INCOMING_MESSAGES
  }

  private _mode?: CommunicationType

  get mode(): CommunicationType {
    return this._mode!
  }

  public set mode(type: CommunicationType) {
    this._mode = type
  }

  get events(): Map<string, IncomingMessage> {
    return this._events
  }

  sendMessage(message: OutgoingMessage): void {
    if (!useConnectionStore().connected || !message) return

    if (this._mode === CommunicationType.IFrameExternalFlashInterface) {
      const frame: HTMLIFrameElement | null = document.getElementById(
        'nitro'
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
      (swfObject as unknown as Window).openroom(JSON.stringify(message))
    }
  }

  onMessage(message: string | MessageEvent): void {
    if (!useConnectionStore().connected || !useConnectionStore().handshake) {
      useConnectionStore().setHandshake(true)
      Logger.info('Handshake completed')
    }

    let json: { header: string; data: never }
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

  onOpen(): void {
    useConnectionStore().setConnected(true)
    setTimeout(() => useConnectionStore().sendComposer(new RequestSessionDataComposer()), 2000)
    Logger.info('connected')
  }

  onClose() {
    useConnectionStore().setConnected(false)
    Logger.info('disconnected')
  }
}
