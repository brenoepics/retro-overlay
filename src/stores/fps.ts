import { computed, ref } from 'vue'
import { defineStore } from 'pinia'

export const useFpsStore = defineStore('fps', () => {
    const times = ref<number[]>([]);
    const rate = computed(() => times.value.length)
    
    function addTime(time: number) {
        times.value.push(time);
        if (times.value.length > 60) {
            times.value.shift();
        }
    }

    return { rate, times, addTime}
})