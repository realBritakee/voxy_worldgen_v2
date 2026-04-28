package com.ethan.voxyworldgenv2.command;

import com.ethan.voxyworldgenv2.core.ChunkGenerationManager;
import com.ethan.voxyworldgenv2.core.Config;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class VoxyGenCommand {

    private static MutableComponent prefix() {
        return Component.literal("Voxygen").withStyle(ChatFormatting.GOLD)
            .append(Component.literal(" | ").withStyle(ChatFormatting.DARK_GRAY));
    }

    private static MutableComponent success(String msg) {
        return prefix().append(Component.literal(msg).withStyle(ChatFormatting.GREEN));
    }

    private static MutableComponent error(String msg) {
        return prefix().append(Component.literal(msg).withStyle(ChatFormatting.RED));
    }

    private static MutableComponent info(String msg) {
        return prefix().append(Component.literal(msg).withStyle(ChatFormatting.GRAY));
    }

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                Commands.literal("voxygen")
                    .requires(source -> source.hasPermission(2))
                    .then(Commands.literal("start")
                        .executes(VoxyGenCommand::executeStart))
                    .then(Commands.literal("stop")
                        .executes(VoxyGenCommand::executeStop))
                    .then(Commands.literal("status")
                        .executes(VoxyGenCommand::executeStatus))
                    .then(Commands.literal("hud")
                        .executes(VoxyGenCommand::executeHud))
            );
        });
    }

    private static int executeStart(CommandContext<CommandSourceStack> ctx) {
        ChunkGenerationManager mgr = ChunkGenerationManager.getInstance();
        if (!mgr.isManuallyPaused()) {
            ctx.getSource().sendSuccess(() -> error("Generation is already running."), false);
            return 0;
        }
        mgr.resumeGeneration();
        Config.DATA.showF3MenuStats = true;
        Config.save();
        ctx.getSource().sendSuccess(() -> success("Generation started.").append(
            Component.literal(" Use /voxygen hud to hide the HUD.").withStyle(ChatFormatting.GRAY)
        ), true);
        return 1;
    }

    private static int executeStop(CommandContext<CommandSourceStack> ctx) {
        ChunkGenerationManager mgr = ChunkGenerationManager.getInstance();
        if (mgr.isManuallyPaused()) {
            ctx.getSource().sendSuccess(() -> error("Generation is already stopped."), false);
            return 0;
        }
        mgr.pauseGeneration();
        Config.DATA.showF3MenuStats = false;
        Config.save();
        ctx.getSource().sendSuccess(() -> success("Generation stopped."), true);
        return 1;
    }

    private static int executeHud(CommandContext<CommandSourceStack> ctx) {
        Config.DATA.showF3MenuStats = !Config.DATA.showF3MenuStats;
        Config.save();
        boolean enabled = Config.DATA.showF3MenuStats;
        ctx.getSource().sendSuccess(() -> enabled ? success("HUD enabled.") : success("HUD disabled."), false);
        return 1;
    }

    private static int executeStatus(CommandContext<CommandSourceStack> ctx) {
        ChunkGenerationManager mgr = ChunkGenerationManager.getInstance();
        boolean running = mgr.isRunning();
        boolean paused = mgr.isManuallyPaused();
        int active = mgr.getActiveTaskCount();
        int remaining = mgr.getRemainingInRadius();
        boolean throttled = mgr.isThrottled();

        String statusStr = paused ? "PAUSED" : (throttled ? "THROTTLED" : "RUNNING");
        ChatFormatting statusColor = paused ? ChatFormatting.RED : (throttled ? ChatFormatting.YELLOW : ChatFormatting.GREEN);

        MutableComponent msg = prefix()
            .append(Component.literal("Status: ").withStyle(ChatFormatting.GRAY))
            .append(Component.literal(statusStr).withStyle(statusColor))
            .append(Component.literal(" | Active: ").withStyle(ChatFormatting.GRAY))
            .append(Component.literal(String.valueOf(active)).withStyle(ChatFormatting.AQUA))
            .append(Component.literal(" | Remaining: ").withStyle(ChatFormatting.GRAY))
            .append(Component.literal(String.valueOf(remaining)).withStyle(ChatFormatting.YELLOW))
            .append(Component.literal(" | Throttled: ").withStyle(ChatFormatting.GRAY))
            .append(Component.literal(String.valueOf(throttled)).withStyle(throttled ? ChatFormatting.RED : ChatFormatting.GREEN));

        MutableComponent finalMsg = msg;
        ctx.getSource().sendSuccess(() -> finalMsg, false);
        return 1;
    }
}
