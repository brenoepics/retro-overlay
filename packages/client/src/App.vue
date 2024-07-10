<script setup lang="ts">
import { useConnectionStore } from '@/stores/connection';
import OverlayComponent from '@/interface/OverlayComponent.vue'

const connection = useConnectionStore()

</script>

<template>
  <OverlayComponent v-if="connection.connected && connection.handshake" />
</template>

<style lang="scss">
@import '@/assets/index.scss';
</style>