import { App, createApp } from 'vue'
import Overlay from '@/App.vue'
import { createPinia } from 'pinia'
import { DraggablePlugin } from '@braks/revue-draggable'

export default class InterfaceManager {
  private _container?: App

  public get container(): App {
    return this._container!
  }

  public initInterface(): void {
    this._container = createApp(Overlay)
    this._container.use(createPinia())
    this._container.use(DraggablePlugin)
    this._container.mount('external-interface')
  }
}
