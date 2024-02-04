import { defineStore } from 'pinia';

export const useEventAlertStore = defineStore('eventalert', () => {
    const open = ref(false);
    const staffUsername = ref('');
    const staffLook = ref('');
    const playerUsername = ref('');
    const eventTitle = ref('');

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

    return { open, staffUsername, staffLook, playerUsername, eventTitle, setStaffUsername, setStaffLook, setPlayerUsername, setEventTitle, setOpen };
});
