package me.overlight.invalidmessagedeny;

import com.sun.tools.jdi.Packet;
import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import org.bukkit.plugin.java.JavaPlugin;

public final class InvalidMessageDeny extends JavaPlugin {

    public static InvalidMessageDeny INSTANCE;
    @Override
    public void onLoad() {
        PacketEvents.create(this);
        PacketEvents.get().getSettings().fallbackServerVersion(PacketEvents.get().getServerUtils().getVersion()).bStats(true).checkForUpdates(false);
        PacketEvents.get().load();
    }

    @Override
    public void onEnable() {
        INSTANCE = this;

        saveDefaultConfig();

        PacketEvents.get().getEventManager().registerListener(new ServerChatListener());
        PacketEvents.get().init();
    }

    @Override
    public void onDisable() {
        PacketEvents.get().terminate();
    }
}
