import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from 'node:path'
import AutoImport from 'unplugin-auto-import/vite'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    AutoImport({
      imports: ['vue-demi', '@vueuse/core'],
      dts: 'src/auto-imports.d.ts',
      eslintrc: {
        enabled: true, 
      },
    }),
  ],
  resolve: {
    alias: [{ find: '@', replacement: resolve(__dirname, './src') }],
  },
})
