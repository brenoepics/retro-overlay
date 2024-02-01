import { createApp } from 'vue'
import './style.css'
import { createPinia } from "pinia"
import App from './App.vue'

const app = createApp(App);

app.use(createPinia());
app.mount("#retro-overlay");