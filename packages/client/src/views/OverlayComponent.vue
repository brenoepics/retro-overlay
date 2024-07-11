<script setup lang="ts">
  import Logger from '@/utils/Logger'
  import PerformanceComponent from './debugging/PerformanceView.vue'

  const debug = ref(Logger.getDebugMode())
</script>

<template>
  <PerformanceComponent v-if="debug" />
</template>
