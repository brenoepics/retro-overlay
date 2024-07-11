<script lang="ts" setup>
  import { useConnectionStore } from '@/stores/connection'
  import OverlayComponent from '@/views/OverlayComponent.vue'

  const connection = useConnectionStore()
</script>

<template>
  <OverlayComponent v-if="connection.connected && connection.handshake" />
</template>

<style lang="scss">
  @import '@/assets/index.scss';
</style>
