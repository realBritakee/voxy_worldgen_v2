package com.ethan.voxyworldgenv2;

import com.ethan.voxyworldgenv2.client.DebugRenderer;
import com.ethan.voxyworldgenv2.core.ChunkGenerationManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;

@Environment(EnvType.CLIENT)
public class VoxyWorldGenV2Client implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        VoxyWorldGenV2.LOGGER.info("initializing voxy world gen v2 client");
        
        // debug hud renderer
        HudRenderCallback.EVENT.register(DebugRenderer::render);

        // register pause check to stop background worker when game is paused
        ChunkGenerationManager.getInstance().setPauseCheck(() -> {
            Minecraft mc = Minecraft.getInstance();
            return mc != null && mc.isPaused();
        });

        // register network receivers
        com.ethan.voxyworldgenv2.network.NetworkClientHandler.init();
        
        // reset connection state on disconnect
        net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            com.ethan.voxyworldgenv2.network.NetworkState.setServerConnected(false);
        });
        
        // tick network stats
        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
            com.ethan.voxyworldgenv2.network.NetworkState.tick();
        });
    }
}
