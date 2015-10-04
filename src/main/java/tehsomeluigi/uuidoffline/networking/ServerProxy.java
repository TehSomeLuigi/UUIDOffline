package tehsomeluigi.uuidoffline.networking;

import net.minecraftforge.common.MinecraftForge;
import tehsomeluigi.uuidoffline.UUIDOffline;

public class ServerProxy extends CommonProxy {

	@Override
	public void registerLoginHandler() {
		MinecraftForge.EVENT_BUS.register(UUIDOffline.lh = new LoginHandler());
		System.out.println("Registered login handler");
	}
	
}
