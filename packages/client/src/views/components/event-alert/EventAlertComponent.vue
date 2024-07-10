<script setup lang="ts">
  import Client from '@/Client.ts'
  import { useEventAlertStore } from '@/stores/eventalert'
  import JoinRoomComposer from '@/communication/outgoing/generic/JoinRoomComposer'

  const eventAlert = useEventAlertStore()

  function joinEvent(roomId: number) {
    Client.Overlay.communicationManager.sendMessage(new JoinRoomComposer(roomId))
  }

  function closeEventAlert() {
    eventAlert.setTransitionClass(false)
    setTimeout(() => {
      eventAlert.setOpen(false)
    }, 500) // 500ms ya da istediğiniz bir süre
  }
</script>
<template>
  <transition name="event-alert-transition">
    <div v-if="eventAlert.open" class="retro-overlay-event-alert">
      <div class="retro-overlay-head">
        <div class="retro-overlay-title">{{ eventAlert.eventTitle }}</div>
      </div>
      <div class="retro-overlay-body">
        <div class="retro-overlay-event-message">
          {{ eventAlert.staffUsername }} invites you to the event!
        </div>
        <div class="retro-overlay-event-buttons">
          <button class="btn close" @click="closeEventAlert">close</button>
          <button class="btn join-event" @click="joinEvent(eventAlert.roomId)">join event</button>
        </div>
      </div>
    </div>
  </transition>
</template>

<style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Quicksand:wght@400;600&display=swap');

  body {
    background: #000;
  }

  .retro-overlay-event-alert {
    margin-left: -10px;
    width: 450px;
    color: #fff;
    font-family: 'Quicksand', sans-serif;
  }

  .retro-overlay-head {
    background: #1E7295;
    text-align: center;
    border-top-left-radius: 80px 80px;
  }

  .retro-overlay-body {
    background: #fff;
    color: #000;
    font-weight: 500;
    margin-bottom: 10px;
    text-align: center;
    padding: 10px;
  }

  .retro-overlay-event-buttons {
    margin-top: 30px;
  }

  .btn {
    border: 0;
    padding: 10px;
    font-family: 'Quicksand', sans-serif;
    font-weight: 800;
    text-transform: uppercase;
    border-radius: 4px;
    transition: background 500ms ease;
  }

  .join-event {
    background: #12db47;
    color: #fff;
  }

  .join-event:hover {
    background: #11ad3a;
    cursor: pointer;
  }

  .close {
    background: #db0f0f;
    color: #fff;
  }

  .close:hover {
    background: #a60d0d;
    cursor: pointer;
  }

  .event-alert-transition-enter-active,
  .event-alert-transition-leave-active {
    transition: opacity 0.5s, transform 0.5s;
  }

  .event-alert-transition-enter-from,
  .event-alert-transition-leave-to {
    opacity: 0;
    transform: translateX(-100px);
  }

  .event-alert-transition-enter-to,
  .event-alert-transition-leave-from {
    opacity: 1;
    transform: translateX(0);
  }
</style>
