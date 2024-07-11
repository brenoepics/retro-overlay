<script lang="ts" setup>
  import { useFpsStore } from '@/stores/fps.ts'
  import MiniMenuComponent from '@/views/components/MiniMenuComponent.vue'

  const fps = useFpsStore()

  onMounted(() => tick())

  const tick = () => {
    const now = performance.now()
    while (fps.times.length > 0 && fps.times[0] <= now - 1000) {
      fps.times.shift()
    }
    fps.times.push(now)
    requestAnimationFrame(tick)
  }
</script>

<template>
  <MiniMenuComponent class="fps-counter">FPS: {{ fps.rate }}</MiniMenuComponent>
</template>

<style scoped>
  .fps-counter {
    color: #ff0000;
  }
</style>
