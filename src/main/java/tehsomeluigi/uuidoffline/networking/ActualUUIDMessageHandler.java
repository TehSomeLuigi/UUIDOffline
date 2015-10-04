package tehsomeluigi.uuidoffline.networking;

import java.lang.reflect.Field;
import java.util.UUID;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;

public class ActualUUIDMessageHandler implements IMessageHandler<ActualUUIDMessage, IMessage> {

	@Override
	public IMessage onMessage(ActualUUIDMessage message, MessageContext ctx) {
		/** might allow someone to crash a server, do not do this
		if (ctx.side != Side.CLIENT) {
			throw new RuntimeException("Message received not on client, ???");
		}
		*/
		
		if (ctx.side == Side.CLIENT) {
			EntityClientPlayerMP ecpmp = Minecraft.getMinecraft().thePlayer;
			try {
				String mcpName = "entityUniqueID";
				String srgName = "field_96093_i";
				
				Field[] fs = Entity.class.getDeclaredFields();
				
				System.out.println("Received UUID");
				
				for (Field f : fs) {
					if (f.getName().equals(mcpName) || f.getName().equals(srgName)) {
						f.setAccessible(true);
						f.set(ecpmp, UUID.fromString(message.uuid));
						
						System.out.println("Assigned UUID");
						
						if (ecpmp == null) {
							throw new RuntimeException("ThePlayer is null!");
						}
					}
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
