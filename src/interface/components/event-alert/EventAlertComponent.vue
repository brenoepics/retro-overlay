<script lang="ts">
import { useEventAlertStore } from '@/stores/eventalert';
import { Component, Vue, toNative } from 'vue-facing-decorator'

@Component
class EventAlertComponent extends Vue {
  eventAlert = useEventAlertStore()
}

export default toNative(EventAlertComponent)
</script>

<template>
  <div class="retro-overlay-event-alert">
    <div class="retro-overlay-head">
      <div class="retro-overlay-title">{{ eventAlert.eventTitle }}</div>
    </div>
    <div class="retro-overlay-body">
      Hello {{ eventAlert.playerUsername }}, {{ eventAlert.staffUsername }} invites you to the event!
    </div>
  </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Quicksand:wght@400;600&display=swap');

body {
  background: #000;
}

.retro-overlay-event-alert {
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
</style>
