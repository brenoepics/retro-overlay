// useEventAlertStore.ts
import { defineStore } from 'pinia';

export const useEventAlertStore = defineStore('eventalert', () => {
    const open = ref(false);
    const staffUsername = ref('');
    const staffLook = ref('');
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
        open, staffUsername, staffLook,
        eventTitle, roomId, transitionClass,
        setStaffUsername, setStaffLook,
        setEventTitle, setOpen, setRoomId, setTransitionClass
    };
});
