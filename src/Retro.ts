import Logger from '@/utils/Logger'
import { CommunicationType } from './communication/CommunicationType'
import FlashExternalInterface from './externalinterface/FlashExternalInterface'
import ExternalInterface from './externalinterface/ExternalInterface'
import Overlay from './externalinterface/Overlay'

declare global {
  interface Window {
    FlashExternalInterface: ExternalInterface
  }
}

export default class Retro {
  private static overlay: Overlay

  constructor(debug: boolean = false) {
    Retro.overlay = new Overlay(debug)
  }

  public init() {
    Retro.overlay.interfaceManager.initInterface()
    this.initExternalFlashInterface()
  }

  public static get Overlay(): Overlay {
    return Retro.overlay
  }
  
  private initExternalFlashInterface = () => {
    const frame: HTMLIFrameElement | null = document.getElementById(
      'nitro',
    ) as HTMLIFrameElement

    //@ts-expect-error NitroConfig comes from index.html
    if (typeof NitroConfig === 'undefined' && !frame) {
      Logger.info('Client not found, External Interface will not run')
      return
    }
    
    if (!window.FlashExternalInterface) {
      window.FlashExternalInterface = new FlashExternalInterface()
    }

    Retro.overlay.communicationManager.mode =
    CommunicationType.IFrameExternalFlashInterface

    if (!frame?.contentWindow) return

    window.addEventListener('message', (ev) => {
      const legacyInterface = 'Nitro_LegacyExternalInterface'

      if (typeof ev.data !== 'string' || !ev.data.startsWith(legacyInterface))
        return

      const { method, params } = JSON.parse(
        ev.data.substring(legacyInterface.length),
      )

      if (!('FlashExternalInterface' in window)) return

      const fn = (
        window.FlashExternalInterface as unknown as Record<
          string,
          (...args: unknown[]) => void
        >
      )[method]
      if (!fn) return

      fn(...params)
    })
  }
}
