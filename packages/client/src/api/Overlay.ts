import InterfaceManager from '@/api/InterfaceManager.ts'
import CommunicationManager, { ICommunicationManager } from '@/communication/CommunicationManager'
import Logger from '@/utils/Logger'

export default class Overlay {
  private _interfaceManager: InterfaceManager = new InterfaceManager();
  private _communicationManager: ICommunicationManager= new CommunicationManager();

  constructor(debug: boolean) {
    Logger.setDebugMode(debug);
  }
  public get interfaceManager() : InterfaceManager {
    return this._interfaceManager;
  }

  public get communicationManager() : ICommunicationManager {
    return this._communicationManager;
  }
}
