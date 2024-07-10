<script setup lang="ts">
import { useFpsStore } from '@/stores/fps';
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
  <div class="fps-counter">FPS: {{ fps.rate }}</div>
</template>

<style scoped>
.fps-counter {
  border: 1px solid rgba(0, 0, 0, 0.29);
  border-radius: 6px;
  background: rgba(46, 46, 44, 0.66);
  box-shadow: inset 0 0 0 2px #fff3;
  color: #ff0000;
  font-family: 'Ubuntu', 'Arial', sans-serif;
  font-size: 16px;
  font-weight: bold;
  padding: 10px;
  margin: 10px;
  text-align: center;
  width: 80px;
}
</style>
