package id.avecraft.songoda.hooks;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

public class ProtocolLibHook {

    private static ProtocolManager protocolManager;

    public static ProtocolManager load(){
        protocolManager = ProtocolLibrary.getProtocolManager();
        return protocolManager;
    }

    public static ProtocolManager getManager() {
        return protocolManager;
    }
}
