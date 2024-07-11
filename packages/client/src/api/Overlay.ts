import InterfaceManager from '@/api/InterfaceManager.ts'
import CommunicationManager from '@/communication/CommunicationManager'
import Logger from '@/utils/Logger'
import { ICommunicationManager } from '@/communication/ICommunicationManager.ts'

export default class Overlay {
  constructor(debug: boolean) {
    Logger.setDebugMode(debug)
  }

  private _interfaceManager: InterfaceManager = new InterfaceManager()

  public get interfaceManager(): InterfaceManager {
    return this._interfaceManager
  }

  private _communicationManager: ICommunicationManager = new CommunicationManager()

  public get communicationManager(): ICommunicationManager {
    return this._communicationManager
  }
}
