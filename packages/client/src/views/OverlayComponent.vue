<script setup lang="ts">
  import Logger from '@/utils/Logger'
  import PerformanceComponent from './components/PerformanceComponent.vue'
  import { useEventAlertStore } from '@/stores/eventalert'
  import EventAlertComponent from './components/event-alert/EventAlertComponent.vue'

  const debug = ref(Logger.getDebugMode())
  const eventAlert = useEventAlertStore()
</script>

<template>
  <div v-if="debug" v-draggable>
    <PerformanceComponent />
    <EventAlertComponent v-if="eventAlert.open" />
  </div>
</template>
