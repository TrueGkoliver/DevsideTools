package com.gkoliver.devsidetools.common.network;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

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
    public static SetItemStackPacket read(FriendlyByteBuf buffer) {
        UUID p1 = buffer.readUUID();
        int p2 = buffer.readInt();
        ItemStack p3 = buffer.readItem();
        return new SetItemStackPacket(p1, p2, p3);
    }
    public static void write(SetItemStackPacket message, FriendlyByteBuf buffer) {
        buffer.writeUUID(message.playerUUID);
        buffer.writeInt(message.stackSlot);
        System.out.println(message.stack);
        buffer.writeItemStack(message.stack, false);
    }
    public static void work(SetItemStackPacket msg, Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        ctx.setPacketHandled(true);
        ServerPlayer sender = ctx.getSender();
        if (!sender.hasPermissions(2)) { System.out.println("Illegal access."); return; }
        Player entity = sender.getLevel().getPlayerByUUID(msg.playerUUID);
        entity.setItemInHand(InteractionHand.MAIN_HAND, msg.stack);
        System.out.println(entity.getItemInHand(InteractionHand.MAIN_HAND));


    }
}
