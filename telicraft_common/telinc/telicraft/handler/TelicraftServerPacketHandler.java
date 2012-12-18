package telinc.telicraft.handler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class TelicraftServerPacketHandler implements IPacketHandler {
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(
				packet.data));
		EntityPlayer sender = (EntityPlayer) player;
	}
}