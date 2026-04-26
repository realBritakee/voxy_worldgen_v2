package com.ethan.voxyworldgenv2.command;

import com.ethan.voxyworldgenv2.core.ChunkGenerationManager;
import com.ethan.voxyworldgenv2.core.Config;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class VoxyGenCommand {

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
        mgr.resumeGeneration();
        Config.DATA.showF3MenuStats = true;
        Config.save();
<<<<<<< Updated upstream
        ctx.getSource().sendSuccess(() -> Component.literal("[VoxyGen] Generation started."), true);
=======
        ctx.getSource().sendSuccess(() -> success("Generation started.").append(
            Component.literal(" Use /voxygen hud to hide the HUD.").withStyle(ChatFormatting.GRAY)
        ), true);
>>>>>>> Stashed changes
        return 1;
    }

    private static int executeStop(CommandContext<CommandSourceStack> ctx) {
        ChunkGenerationManager mgr = ChunkGenerationManager.getInstance();
        mgr.pauseGeneration();
        Config.DATA.showF3MenuStats = false;
        Config.save();
<<<<<<< Updated upstream
        ctx.getSource().sendSuccess(() -> Component.literal("[VoxyGen] Generation stopped."), true);
        return 1;
    }

    private static int executeHud(CommandContext<CommandSourceStack> ctx) {
        Config.DATA.showF3MenuStats = !Config.DATA.showF3MenuStats;
        Config.save();
        boolean enabled = Config.DATA.showF3MenuStats;
        ctx.getSource().sendSuccess(() -> Component.literal(enabled ? "[VoxyGen] HUD enabled." : "[VoxyGen] HUD disabled."), false);
=======
        ctx.getSource().sendSuccess(() -> success("Generation stopped."), true);
>>>>>>> Stashed changes
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
        int active = mgr.getActiveTaskCount();
        int remaining = mgr.getRemainingInRadius();
        boolean throttled = mgr.isThrottled();
        String status = running ? "RUNNING" : "STOPPED";
        String msg = String.format("[VoxyGen] Status: %s | Active tasks: %d | Remaining in radius: %d | Throttled: %s",
                status, active, remaining, throttled);
        ctx.getSource().sendSuccess(() -> Component.literal(msg), false);
        return 1;
    }
}
