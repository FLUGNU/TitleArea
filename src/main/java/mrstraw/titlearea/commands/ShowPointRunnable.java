package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPoint;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ShowPointRunnable extends BukkitRunnable {

    private final InterestPoint interestPoint;


    public ShowPointRunnable(InterestPoint interestPoint) {
        this.interestPoint = interestPoint;
    }

    @Override
    public void run() {

        World world = interestPoint.getLocation().getWorld();
        Location location = interestPoint.getLocation();
        Integer distance = interestPoint.getDistance();
        for(Double theta=0D; theta < 2 * Math.PI ; theta += 2*Math.PI/32){
            for(Double phi = 0D ; theta < 2 * Math.PI ; phi += 2*Math.PI/32){
                world.spawnParticle(Particle.FLAME,distance*Math.sin(theta)*Math.cos(phi),distance*Math.cos(theta)*Math.sin(phi),distance*Math.cos(theta),1);
            }
        }
    }

}