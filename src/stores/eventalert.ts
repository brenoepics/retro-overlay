// useEventAlertStore.ts
import { defineStore } from 'pinia';

export const useEventAlertStore = defineStore('eventalert', () => {
    const open = ref(false);
    const staffUsername = ref('');
    const staffLook = ref('');
    const playerUsername = ref('');
    const eventTitle = ref('');
    const roomId = ref(0);
    const transitionClass = ref('');

    function setOpen(windowOpen: boolean) {
        open.value = windowOpen;
    }

    function setStaffUsername(username: string) {
        staffUsername.value = username;
    }

    function setStaffLook(look: string) {
        staffLook.value = look;
    }

    function setPlayerUsername(username: string) {
        playerUsername.value = username;
    }

    function setEventTitle(title: string) {
        eventTitle.value = title;
    }

    function setRoomId(id: number) {
        roomId.value = id;
    }

    function setTransitionClass(value: boolean) {
        transitionClass.value = value ? 'event-alert-transition-enter-active' : 'event-alert-transition-leave-active';
    }

    return {
        open, staffUsername, staffLook, playerUsername,
        eventTitle, roomId, transitionClass,
        setStaffUsername, setStaffLook, setPlayerUsername,
        setEventTitle, setOpen, setRoomId, setTransitionClass
    };
});
