package tehsomeluigi.uuidoffline;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.init.Blocks;
import tehsomeluigi.uuidoffline.command.UUIDOfflineCommand;
import tehsomeluigi.uuidoffline.networking.ActualUUIDMessage;
import tehsomeluigi.uuidoffline.networking.ActualUUIDMessageHandler;
import tehsomeluigi.uuidoffline.networking.CommonProxy;
import tehsomeluigi.uuidoffline.networking.LoginHandler;

@Mod(modid = UUIDOffline.MODID, version = UUIDOffline.VERSION)
public class UUIDOffline {
	public static final String MODID = "uuidoffline";
	public static final String VERSION = "1.0";
	
	public static SimpleNetworkWrapper snw;
	public static LoginHandler lh;
	
	@SidedProxy(serverSide = "tehsomeluigi.uuidoffline.networking.ServerProxy", clientSide = "tehsomeluigi.uuidoffline.networking.ClientProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		snw = NetworkRegistry.INSTANCE.newSimpleChannel(UUIDOffline.MODID);
		snw.registerMessage(ActualUUIDMessageHandler.class, ActualUUIDMessage.class, 0, Side.CLIENT);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		// some example code
		System.out.println("DIRT BLOCK >> " + Blocks.dirt.getUnlocalizedName());
		
		proxy.registerLoginHandler();
	}

	@EventHandler
	public void serverLoading(FMLServerStartingEvent event) {
		event.registerServerCommand(new UUIDOfflineCommand());
	}
}
