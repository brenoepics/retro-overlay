import PongComposer from '@/communication/outgoing/generic/PongComposer';
import IncomingMessage from '../IncomingMessage';
import Retro from '@/Retro';

interface PingData {
    message: string;
}

export default class PingEvent implements IncomingMessage {
    parse(data: PingData): void {
        Retro.Overlay.communicationManager.sendMessage(new PongComposer());
    }
}