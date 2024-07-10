import { useEventAlertStore } from '@/stores/eventalert';
import IncomingMessage from '../IncomingMessage';

interface EventAlertData {
    staffUsername: string;
    staffLook: string;
    playerUsername: string;
    eventTitle: string;
    roomId: number;
}

export default class EventAlertEvent implements IncomingMessage {
    parse(data: EventAlertData): void {
        useEventAlertStore().setOpen(true);
        useEventAlertStore().setStaffUsername(data.staffUsername);
        useEventAlertStore().setStaffLook(data.staffLook);
        useEventAlertStore().setEventTitle(data.eventTitle);
        useEventAlertStore().setRoomId(data.roomId);
    }
}
