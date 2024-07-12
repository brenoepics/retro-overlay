import { ref } from 'vue'
import { defineStore } from 'pinia'
import OutgoingMessage from '@/communication/outgoing/OutgoingMessage.ts'
import Client from '@/Client.ts'

export const useConnectionStore = defineStore('connection', () => {
  const connected = ref(false)
  const handshake = ref(false)

  function sendComposer(message: OutgoingMessage) {
    Client.Overlay.communicationManager.sendMessage(message)
  }

  function setConnected(isConnected: boolean) {
    connected.value = isConnected
  }

  function setHandshake(isHandshake: boolean) {
    handshake.value = isHandshake
  }

  return { connected, setConnected, handshake, setHandshake, sendComposer }
})
