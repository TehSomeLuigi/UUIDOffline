package tehsomeluigi.uuidoffline.networking;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class ActualUUIDMessage implements IMessage {

	public String uuid;

	public ActualUUIDMessage() {
	}
	
	public ActualUUIDMessage(String uuid) {
		this.uuid = uuid;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		uuid = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, uuid);
	}

}
