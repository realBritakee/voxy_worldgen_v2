package com.ethan.voxyworldgenv2.core;

import com.ethan.voxyworldgenv2.network.NetworkHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.LevelChunk;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import java.util.Set;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChunkUpdateTracker {
    private static final ChunkUpdateTracker INSTANCE = new ChunkUpdateTracker();
    private final Map<ResourceKey<Level>, Set<Long>> dirtyChunks = new ConcurrentHashMap<>();
    private final Map<ResourceKey<Level>, Long> lastProcessTimes = new ConcurrentHashMap<>();

    private ChunkUpdateTracker() {}

    public static ChunkUpdateTracker getInstance() {
        return INSTANCE;
    }

    public void markDirty(LevelChunk chunk) {
        dirtyChunks.computeIfAbsent(chunk.getLevel().dimension(), k -> ConcurrentHashMap.newKeySet())
                .add(chunk.getPos().toLong());
    }

    public void processDirty(ServerLevel level) {
        if (level == null) return;
        
        Set<Long> levelDirty = dirtyChunks.get(level.dimension());
        if (levelDirty == null || levelDirty.isEmpty()) return;

        // throttle processing to every 2 seconds (40 ticks)
        long now = System.currentTimeMillis();
        long lastTime = lastProcessTimes.getOrDefault(level.dimension(), 0L);
        if (now - lastTime < 2000) return;
        lastProcessTimes.put(level.dimension(), now);

        Set<Long> toProcess = new java.util.HashSet<>(levelDirty);
        
        for (long posLong : toProcess) {
            ChunkPos pos = new ChunkPos(posLong);
            LevelChunk chunk = level.getChunkSource().getChunk(pos.x, pos.z, false);
            if (chunk != null) {
                NetworkHandler.broadcastLODData(chunk);
            }
        }
        
        // remove only what we processed to avoid losing concurrent additions
        levelDirty.removeAll(toProcess);
    }
}
