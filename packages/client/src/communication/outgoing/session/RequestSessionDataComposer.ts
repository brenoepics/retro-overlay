import OutgoingMessage from '../OutgoingMessage'

export default class RequestSessionDataComposer implements OutgoingMessage {
  header: string = 'request_session_data'
  data: unknown = {}
}
