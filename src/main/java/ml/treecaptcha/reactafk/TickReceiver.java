package ml.treecaptcha.reactafk;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Collection;

import static ml.treecaptcha.reactafk.Reactafk.*;

public class TickReceiver extends BukkitRunnable {

    @Override
    public void run() {
        Collection<? extends Player> playerList = Bukkit.getOnlinePlayers();
        double tpsMinute = Bukkit.getTPS()[0];
        for (Player p: playerList){
            if (playerSecondsMap.containsKey(p.getUniqueId())){
                SecondAngle pair = playerSecondsMap.get(p.getUniqueId());
                if (playerMoved(p)){
                    pair.seconds = 0;
                }
                else {
                    pair.seconds += 1;
                    if (pair.seconds == SecondsTilWarn){
                        p.sendPlainMessage(warnMessage);
                    }
                }
            }

            else {
                playerSecondsMap.put(p.getUniqueId(), new SecondAngle(p.getLocation().getDirection()));
            }
            if(TPSforKick >= tpsMinute){
                // check if they are ready to be kicked!
                if (SecondsTilKick <= playerSecondsMap.get(p.getUniqueId()).seconds){
                    p.kick(Component.text("Kicked for idling for too long while the tps was below " + TPSforKick + "!"));
                }
            }
        }
    }

    /**
     * updates stored player position and computes whether they have turned since the last check
     * @param p the player to check
     * @return whether they have moved
     */
    private static boolean playerMoved(Player p){
        SecondAngle pair = playerSecondsMap.get(p.getUniqueId());
        Vector pLoc = p.getLocation().getDirection();
        if (pLoc.equals(pair.angle)){
            pair.angle = pLoc;
            return false;
        }
        pair.angle = pLoc;
        return true;
    }
}
