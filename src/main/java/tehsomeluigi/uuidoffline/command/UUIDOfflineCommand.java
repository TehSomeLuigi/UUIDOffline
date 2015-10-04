package tehsomeluigi.uuidoffline.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.UsernameCache;

public class UUIDOfflineCommand implements ICommand {
	
	private List<String> aliases;
	
	public UUIDOfflineCommand() {
		aliases = new ArrayList<String>();
		aliases.add("uuidoffline");
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		return "uuidoffline";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "uuidoffline [username*] — prints UUID";
	}

	@Override
	public List getCommandAliases() {
		return aliases;
	}
	
	public static UUID getPlayerUUID(String username) {
        for (Map.Entry<UUID, String> entry : UsernameCache.getMap().entrySet()) {
            if (entry.getValue().equalsIgnoreCase(username)) {
                return entry.getKey();
            }
        }
        return null;
    }
	
	@Override
	public void processCommand(ICommandSender cs, String[] args) {
		//System.err.println(p_71515_1_.getClass().getName());
		
		if (args.length >= 1) {
			
			UUID uuid = getPlayerUUID(args[0]);
			
			if (uuid == null) {
				cs.addChatMessage(new ChatComponentText("They have no UUID."));
			} else {
				cs.addChatMessage(new ChatComponentText("Their UUID is: " + uuid.toString()));
			}
			
		} else if (cs instanceof EntityPlayerMP) {
			EntityPlayerMP pmp = (EntityPlayerMP) cs;
			UUID uuid = pmp.getPersistentID();
			UUID entityUUID = pmp.getUniqueID();
			if (uuid == null) {
				cs.addChatMessage(new ChatComponentText("You have no UUID."));
			} else {
				cs.addChatMessage(new ChatComponentText("Your Persistent UUID is: " + uuid.toString()));
				cs.addChatMessage(new ChatComponentText("Your Entity UUID is: " + entityUUID.toString()));
			}
		} else {
			cs.addChatMessage(new ChatComponentText("This command can only be used by players if you do not specify a username."));
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		// TODO Auto-generated method stub
		return false;
	}

}
