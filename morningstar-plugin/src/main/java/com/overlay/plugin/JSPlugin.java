package com.overlay.plugin;

import com.overlay.plugin.audio.RoomAudioManager;
import com.overlay.plugin.communication.CommunicationManager;

public class JSPlugin {
    private static JSPlugin INSTANCE;

    private final RoomAudioManager roomAudioManager;
    private final CommunicationManager communicationManager;

    public JSPlugin() {
        this.roomAudioManager = new RoomAudioManager();
        this.communicationManager = new CommunicationManager();
    }

    public static void init() {
        JSPlugin.INSTANCE = new JSPlugin();
    }

    public RoomAudioManager getRoomAudioManager() {
        return roomAudioManager;
    }

    public CommunicationManager getCommunicationManager() {
        return communicationManager;
    }

    public static JSPlugin getInstance() {
        return JSPlugin.INSTANCE;
    }

    public void dispose() {
        getInstance().communicationManager.dispose();
        getInstance().roomAudioManager.dispose();
    }
}
