import InterfaceManager from '@/interface/InterfaceManager';
import CommunicationManager from '@/communication/CommunicationManager';
import Logger from '@/utils/Logger';

export default class Overlay {
  private _interfaceManager: InterfaceManager = new InterfaceManager();
  private _communicationManager: CommunicationManager= new CommunicationManager();

  constructor(debug: boolean) {
    Logger.setDebugMode(debug);
  }
  public get interfaceManager() : InterfaceManager {
    return this._interfaceManager;
  }

  public get communicationManager() : CommunicationManager {
    return this._communicationManager;
  }
}