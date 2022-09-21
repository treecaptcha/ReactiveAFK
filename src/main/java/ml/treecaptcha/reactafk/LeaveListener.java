package ml.treecaptcha.reactafk;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static ml.treecaptcha.reactafk.Reactafk.playerSecondsMap;

public class LeaveListener implements Listener {

    @EventHandler
    public void onPlayerLeave(org.bukkit.event.player.PlayerQuitEvent event) {
        if (playerSecondsMap.containsKey(event.getPlayer().getUniqueId())){
            SecondAngle a = playerSecondsMap.get(event.getPlayer().getUniqueId());
            a.seconds = 0;
        }
    }
}
