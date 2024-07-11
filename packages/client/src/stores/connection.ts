import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useConnectionStore = defineStore('connection', () => {
  const connected = ref(false)
  const handshake = ref(false)

  function setConnected(isConnected: boolean) {
    connected.value = isConnected
  }

  function setHandshake(isHandshake: boolean) {
    handshake.value = isHandshake
  }

  return { connected, setConnected, handshake, setHandshake }
})
