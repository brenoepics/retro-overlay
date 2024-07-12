<script lang="ts" setup>
  import { onMounted } from 'vue'
  import { useFpsStore } from '@/stores/fps.ts'
  import MiniMenuComponent from '@/views/components/MiniMenuComponent.vue'
  import { useConnectionStore } from '@/stores/connection.ts'
  import HelloWorldComposer from '@/communication/outgoing/generic/HelloWorldComposer.ts'

  const connection = useConnectionStore()
  const fps = useFpsStore()
  const tick = () => {
    const now = performance.now()
    while (fps.times.length > 0 && fps.times[0] <= now - 1000) {
      fps.times.shift()
    }
    fps.times.push(now)
    requestAnimationFrame(tick)
  }

  const hello = () => {
    connection.sendComposer(new HelloWorldComposer(performance.now()))
  }

  onMounted(() => tick())
</script>

<template>
  <MiniMenuComponent>
    <div class="fps-counter">
      <h5>FPS: {{ fps.rate }}
      </h5>
      <button class="btn" @click="hello()">Send Hello World</button>
    </div>
  </MiniMenuComponent>
</template>

<style scoped>
  .fps-counter {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    color: #ff0000;
  }
</style>
