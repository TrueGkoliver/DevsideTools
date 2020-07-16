package com.gkoliver.devsidetools.common.network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;
import org.lwjgl.system.windows.MSG;

import java.util.UUID;
import java.util.function.Supplier;

public class SetItemStackPacket {
    public final UUID playerUUID;
    public final int stackSlot;
    public final ItemStack stack;
    public SetItemStackPacket(UUID playerUUIDIn, int stackSlotIn, ItemStack stackIn) {
        playerUUID = playerUUIDIn;
        stackSlot = stackSlotIn;
        stack = stackIn;
    }
    public static SetItemStackPacket read(PacketBuffer buffer) {
        UUID p1 = buffer.readUniqueId();
        int p2 = buffer.readInt();
        ItemStack p3 = buffer.readItemStack();
        return new SetItemStackPacket(p1, p2, p3);
    }
    public static void write(SetItemStackPacket message, PacketBuffer buffer) {
        buffer.writeUniqueId(message.playerUUID);
        buffer.writeInt(message.stackSlot);
        buffer.writeItemStack(message.stack);
    }
    public static void work(SetItemStackPacket msg, Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        ctx.setPacketHandled(true);
        ServerPlayerEntity sender = ctx.getSender();
        if (!sender.hasPermissionLevel(2)) { System.out.println("Illegal access."); return; }
        PlayerEntity entity = sender.getServerWorld().getPlayerByUuid(msg.playerUUID);
        entity.setHeldItem(Hand.MAIN_HAND, msg.stack);
        System.out.println(entity.getHeldItem(Hand.MAIN_HAND));


    }
}
