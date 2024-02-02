<script lang="ts">
import Logger from '@/utils/Logger';
import PerformanceComponent from './components/PerformanceComponent.vue';
import { Component, Vue, toNative } from 'vue-facing-decorator'

@Component(
  {
    components: { PerformanceComponent }
  })
class OverlayComponent extends Vue {
  debug = Logger.getDebugMode();
}
export default toNative(OverlayComponent)
</script>

<template>
  <div v-if="debug" v-draggable>
    <PerformanceComponent />
  </div>
</template>