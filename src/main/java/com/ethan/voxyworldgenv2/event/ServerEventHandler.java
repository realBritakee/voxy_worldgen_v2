package com.ethan.voxyworldgenv2.event;

import com.ethan.voxyworldgenv2.VoxyWorldGenV2;
import com.ethan.voxyworldgenv2.core.ChunkGenerationManager;
import com.ethan.voxyworldgenv2.core.PlayerTracker;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public final class ServerEventHandler {
    private ServerEventHandler() {}
    
    public static void onServerStarted(MinecraftServer server) {
        VoxyWorldGenV2.LOGGER.info("server started, initializing manager");
        ChunkGenerationManager.getInstance().initialize(server);
    }
    
    public static void onServerStopping(MinecraftServer server) {
        VoxyWorldGenV2.LOGGER.info("server stopping, shutting down manager");
        ChunkGenerationManager.getInstance().shutdown();
        PlayerTracker.getInstance().clear();
    }
    
    public static void onPlayerJoin(ServerGamePacketListenerImpl handler, PacketSender sender, MinecraftServer server) {
        PlayerTracker.getInstance().addPlayer(handler.getPlayer());
        com.ethan.voxyworldgenv2.network.NetworkHandler.sendHandshake(handler.getPlayer());
    }
    
    public static void onPlayerDisconnect(ServerGamePacketListenerImpl handler, MinecraftServer server) {
        PlayerTracker.getInstance().removePlayer(handler.getPlayer());
    }
    
    public static void onServerTick(MinecraftServer server) {
        ChunkGenerationManager.getInstance().tick();
    }
}
