package net.asbyth.pumpkinoverlay.command;

import net.asbyth.pumpkinoverlay.PumpkinOverlay;
import net.asbyth.pumpkinoverlay.config.Options;
import net.asbyth.pumpkinoverlay.render.RenderOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class OverlayCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "pumpkinoverlay";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Usage: /" + getCommandName();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Options.PUMPKIN_OVERLAY = !Options.PUMPKIN_OVERLAY;
        PumpkinOverlay.INSTANCE.saveConfig();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}
