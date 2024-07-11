import Logger from '@/utils/Logger'
import { CommunicationType } from './communication/CommunicationType'
import IFlashExternal from '@/api/IFlashExternal.ts'
import Overlay from '@/api/Overlay'

function isAllowedOrigin(origin: string): boolean {
  return origin.length > 0;
}

export default class Client {
  private static overlay: Overlay

  constructor(debug: boolean = false) {
    Client.overlay = new Overlay(debug)
  }

  public init() {
    Client.overlay.interfaceManager.initInterface()
    this.initExternalFlashInterface()
  }

  public static get Overlay(): Overlay {
    return Client.overlay
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
      window.FlashExternalInterface = new IFlashExternal()
    }

    Client.overlay.communicationManager.mode =
    CommunicationType.IFrameExternalFlashInterface

    if (!frame?.contentWindow) return

    window.addEventListener('message', (ev) => {
      const legacyInterface = 'Nitro_LegacyExternalInterface'

      if (typeof ev.data !== 'string' || !ev.data.startsWith(legacyInterface) || !isAllowedOrigin(ev.origin))
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
