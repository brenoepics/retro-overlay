import IExternal from '@/api/IExternal.ts'

declare global {
  interface Window {
    FlashExternalInterface: IExternal
    openroom: (message: string) => void
  }
}
