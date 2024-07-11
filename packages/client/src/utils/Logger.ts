export default class Logger {
  private static DebugMode = true

  public static setDebugMode(debugMode: boolean) {
    Logger.DebugMode = debugMode
  }

  public static getDebugMode() {
    return Logger.DebugMode
  }

  public static info(msg: string) {
    Logger.log(msg)
  }

  public static debug(msg: string) {
    if (Logger.DebugMode) Logger.log(msg)
  }

  private static log(msg: string) {
    try {
      const date = new Date()
      const formattedDate = date.toLocaleTimeString()
      console.log('[' + formattedDate + '] ' + msg)
    } catch (error) {
      console.error('Error while logging:', error)
    }
  }
}
