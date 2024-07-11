import { computed, ref } from 'vue'
import { defineStore } from 'pinia'

export const useFpsStore = defineStore('fps', () => {
  const times = ref<number[]>([])
  const rate = computed(() => times.value.length)
  return { rate, times }
})
