<script lang="ts">
import Logger from '@/utils/Logger';
import PerformanceComponent from './components/PerformanceComponent.vue';
import { Component, Vue, toNative } from 'vue-facing-decorator'
import { useEventAlertStore } from '@/stores/eventalert';
import EventAlertComponent from './components/event-alert/EventAlertComponent.vue';

@Component(
  {
    components: { PerformanceComponent }
  })
class OverlayComponent extends Vue {
  debug = Logger.getDebugMode();
  eventAlert = useEventAlertStore();
}
export default toNative(OverlayComponent)
</script>

<template>
  <div v-if="debug" v-draggable>
    <PerformanceComponent />
    <EventAlertComponent v-if="eventAlert.open" />
  </div>
</template>