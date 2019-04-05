package net.asbyth.pumpkinoverlay;

import net.asbyth.pumpkinoverlay.command.OverlayCommand;
import net.asbyth.pumpkinoverlay.config.Options;
import net.asbyth.pumpkinoverlay.render.RenderOverlay;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(
        modid = PumpkinOverlay.MOD_ID,
        name = PumpkinOverlay.MOD_NAME,
        version = PumpkinOverlay.VERSION,
        clientSideOnly = true,
        acceptedMinecraftVersions = "1.8.9"
)
public class PumpkinOverlay {

    static final String MOD_ID = "pumpkinoverlay";
    static final String MOD_NAME = "PumpkinOverlay";
    static final String VERSION = "1.0";

    @Mod.Instance(MOD_ID)
    public static PumpkinOverlay INSTANCE;

    private File file;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new OverlayCommand());
        file = new File(Minecraft.getMinecraft().mcDataDir, "pumpkinoverlay/PumpkinOverlay.config");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        loadConfig();
        MinecraftForge.EVENT_BUS.register(new RenderOverlay(Minecraft.getMinecraft()));
    }

    public void saveConfig() {
        Configuration config = new Configuration(file);
        updateConfig(config, false);
        config.save();
    }

    private void loadConfig() {
        Configuration config = new Configuration(file);
        config.load();
        updateConfig(config, true);
    }

    private void updateConfig(Configuration config, boolean load) {
        Property prop = config.get("General", "pumpkinOverlay", false);
        if (load) {
            Options.PUMPKIN_OVERLAY = prop.getBoolean();
        } else {
            prop.setValue(Options.PUMPKIN_OVERLAY);
        }
    }
}
