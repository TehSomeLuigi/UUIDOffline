package tehsomeluigi.uuidoffline.networking;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import tehsomeluigi.uuidoffline.UUIDOffline;

public class LoginHandler {
	@SubscribeEvent(priority = EventPriority.LOW)
	public void onPlayerLoggedIn(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) event.entity;
			System.out.println("UUID of player: " + player.getUniqueID());
			System.out.println("ID of player: " + event.entity.getEntityId());
			
			UUIDOffline.snw.sendTo(new ActualUUIDMessage(player.getUniqueID().toString()), player);
		}
	}
}
