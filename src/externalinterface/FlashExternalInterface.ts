import Retro from '@/Retro'
import ExternalInterface from './ExternalInterface'
import Logger from '@/utils/Logger'

export default class FlashExternalInterface implements ExternalInterface {
  /* TODO: add everything on the FlashExternalInterface class */

  disconnect(reasonCode: number, reasonString: string): void {
    Retro.Overlay.communicationManager.onClose()
    Logger.info(`Disconnect: ${reasonCode} ${reasonString}`)
  }

  legacyTrack(category: string, action: string, data: unknown[]): void {
    if (category === 'authentication') {
      Retro.Overlay.communicationManager.onOpen()
    }

    Logger.debug(`Legacy track: ${category} ${action} ${data}`)
  }

  logDebug(message: string): void {
    Logger.debug('Debug: ' + message)
  }

  logWarn(message: string): void {
    Logger.info('Warn: ' + message)
  }

  logCrash(message: string): void {
    Logger.info('Crashed: ' + message)
  }

  logError(message: string): void {
    Logger.info('Error: ' + message)
  }

  openPage(pageUrl: string): void {
    window.open(pageUrl, '_blank')
  }

  openHabblet(message: string, param: string): void {
    Retro.Overlay.communicationManager.onMessage(message)
    Logger.debug('Received message: ' + message + ' with param: ' + param)
  }

  openWebPageAndMinimizeClient(pageUrl: string): void {
    const newWindow = window.open(pageUrl, '_blank')
    if (newWindow) {
      newWindow.focus()
    }
  }

  roomVisited(roomId: number): void {
    Logger.debug('Room visited: ' + roomId)
  }
}
