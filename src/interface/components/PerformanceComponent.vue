<script lang="ts">
import { useFpsStore } from '@/stores/fps';
import { Component, Vue, toNative } from 'vue-facing-decorator'

@Component
class PerformanceComponent extends Vue {
  fps = useFpsStore()

  data() {
    return {
      fps: {
        times: [],
        fps: 0
      }
    }
  }
  mounted() {
    this.tick();
  }

  tick() {
    const now = performance.now();

    while (this.fps.times.length > 0 && this.fps.times[0] <= now - 1000) {
      this.fps.times.shift();
    }

    this.fps.times.push(now);
    requestAnimationFrame(this.tick);
  }
}

export default toNative(PerformanceComponent)
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
