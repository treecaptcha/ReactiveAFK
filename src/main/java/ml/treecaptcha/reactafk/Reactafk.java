package ml.treecaptcha.reactafk;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Reactafk extends JavaPlugin {
    public static Map<UUID, SecondAngle> playerSecondsMap = new HashMap();

    public static int SecondsTilWarn;
    public static int SecondsTilKick;
    public static int TPSforKick;

    public static String warnMessage;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        SecondsTilWarn = getConfig().getInt("afk-warn-time") * 60;
        SecondsTilKick = getConfig().getInt("afk-kick-time") * 60;
        TPSforKick = getConfig().getInt("max-tps");
        warnMessage = "[§6Anti-AFK§r] §cYou will be kicked in " + ((SecondsTilKick - SecondsTilWarn)/60) + " minutes if the tps is below " + TPSforKick + "!";
        new TickReceiver().runTaskTimer(this, 20, 20);
        // new Lagger().runTaskTimer(this, 20, 1);
        Bukkit.getServer().getPluginManager().registerEvents(new LeaveListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
