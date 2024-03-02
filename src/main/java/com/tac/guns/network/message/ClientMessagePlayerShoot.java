package com.tac.guns.network.message;

import com.tac.guns.api.event.GunShootEvent;
import com.tac.guns.api.item.IGun;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientMessagePlayerShoot {
    public static void encode(ClientMessagePlayerShoot message, FriendlyByteBuf buf) {
    }

    public static ClientMessagePlayerShoot decode(FriendlyByteBuf buf) {
        return new ClientMessagePlayerShoot();
    }

    public static void handle(ClientMessagePlayerShoot message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        if (context.getDirection().getReceptionSide().isServer()) {
            context.enqueueWork(() -> {
                ServerPlayer entity = context.getSender();
                if (entity == null || !(entity.getMainHandItem().getItem() instanceof IGun gun)) {
                    return;
                }
                if (MinecraftForge.EVENT_BUS.post(new GunShootEvent(entity, entity.getMainHandItem(), LogicalSide.SERVER))) {
                    return;
                }
                gun.shoot(entity, entity.getMainHandItem());
            });
        }
        context.setPacketHandled(true);
    }
}
