export default interface ExternalInterface {
    legacyTrack?: (
        category: string,
        action: string,
        data: unknown[]
    ) => void;
    logDebug?: (...params: string[]) => void;
    disconnect?: (reasonCode: number, reasonString: string) => void;
    logout?: () => void;
    openWebPageAndMinimizeClient?: (pageUrl: string) => void;
    heartBeat?: () => void;
    logEventLog?: (log: string) => void;
    openPage?: (pageUrl: string) => void;
    closeWebPageAndRestoreClient?: () => void;
    openHabblet?: (name: string, param: string) => void;
    closeHabblet?: (name: string, param: string) => void;
    openExternalLink?: (link: string) => void;
    roomVisited?: (roomId: number) => void;
    openMinimail?: (target: string) => void;
    openNews?: () => void;
    closeNews?: () => void;
    openAvatars?: () => void;
    openRoomEnterAd?: () => void;
    updateFigure?: (figure: string) => void;
}