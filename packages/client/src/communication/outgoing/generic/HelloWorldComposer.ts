import OutgoingMessage from '../OutgoingMessage'

interface HelloWorldData {
  random: number;
}

export default class HelloWorldComposer implements OutgoingMessage {
  header: string = 'client_hello'
  data: HelloWorldData

  constructor(clientHello: number) {
    this.data = {
      'random': clientHello
    }
  }
}
