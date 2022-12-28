package me.overlight.invalidmessagedeny;

import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.chat.WrappedPacketInChat;

public class ServerChatListener
        extends PacketListenerAbstract {
    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent event) {
        if(event.getPacketId() == PacketType.Play.Server.CHAT){
            WrappedPacketInChat chat = new WrappedPacketInChat(event.getNMSPacket());
            if(InvalidMessageDeny.INSTANCE.getConfig().getStringList("deniedMessages").contains(chat.getMessage())){
                event.setCancelled(true);
            }
        }
    }
}
