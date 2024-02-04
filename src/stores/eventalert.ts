import { defineStore } from 'pinia';

export const useEventAlertStore = defineStore('eventalert', () => {
    let open = false;
    let staffUsername = '';
    let staffLook = '';
    let playerUsername = '';
    let eventTitle = '';

    function setOpen(windowOpen: boolean) {
        open = windowOpen;
    }

    function setStaffUsername(username: string) {
        staffUsername = username;
    }

    function setStaffLook(look: string) {
        staffLook = look;
    }

    function setPlayerUsername(username: string) {
        playerUsername = username;
    }

    function setEventTitle(title: string) {
        eventTitle = title;
    }

    return { open, staffUsername, staffLook, playerUsername, eventTitle, setStaffUsername, setStaffLook, setPlayerUsername, setEventTitle, setOpen };
});
