<script setup lang="ts">

</script>

<template>
  <div class="overlay-menu" v-draggable>
    <slot>Menu</slot>
  </div>
</template>

<style scoped>
  .overlay-menu {
    border: 1px solid rgba(0, 0, 0, 0.29);
    border-radius: 6px;
    background: rgba(46, 46, 44, 0.66);
    box-shadow: inset 0 0 0 2px #fff3;
    font-family: 'Ubuntu', 'Arial', sans-serif;
    font-size: 16px;
    font-weight: bold;
    padding: 10px;
    margin: 10px;
    text-align: center;
    width: 80px;
  }
</style>
