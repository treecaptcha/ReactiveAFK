package ml.treecaptcha.reactafk;

import org.bukkit.scheduler.BukkitRunnable;

public class Lagger extends BukkitRunnable {

    @Override
    public void run() {
        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
