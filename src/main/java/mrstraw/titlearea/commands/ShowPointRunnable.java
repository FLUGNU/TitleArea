package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPoint;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ShowPointRunnable implements Runnable {

    private InterestPoint interestPoint;


    public ShowPointRunnable(InterestPoint interestPoint) {
        this.interestPoint = interestPoint;
    }

    @Override
    public void run() {
        World world = interestPoint.getLocation().getWorld();
        Location location = interestPoint.getLocation();
        Integer distance = interestPoint.getDistance();
        Integer radius = interestPoint.getRadius();
        for(Double theta=0D; theta < 2 * Math.PI ; theta += Math.min(Math.max(Math.PI/10,Math.PI/distance),Math.PI/3)){
            for(Double phi = 0D ; phi < 2 * Math.PI ; phi += Math.min(Math.max(Math.PI/10,Math.PI/distance),Math.PI/3)){

                world.spawnParticle(Particle.FLAME
                        ,location.getX()+distance*Math.cos(theta)*Math.cos(phi)
                        ,location.getY()+distance*Math.sin(theta)
                        ,location.getZ()+distance*Math.cos(theta)*Math.sin(phi)
                        ,1,0,0,0,0);
                //Bukkit.broadcastMessage(Particle.FLAME+"Location: "+location);
            }
        }
        for(Double theta=0D; theta < 2 * Math.PI ; theta += Math.min(Math.max(Math.PI/10,Math.PI/radius),Math.PI/3)){
            for(Double phi = 0D ; phi < 2 * Math.PI ; phi += Math.min(Math.max(Math.PI/10,Math.PI/radius),Math.PI/3)){
                world.spawnParticle(Particle.CRIT_MAGIC
                        ,location.getX()+radius*Math.cos(theta)*Math.cos(phi)
                        ,location.getY()+radius*Math.sin(theta)
                        ,location.getZ()+radius*Math.cos(theta)*Math.sin(phi)
                        ,1,0,0,0,0);
                //Bukkit.broadcastMessage(Particle.FLAME+"Location: "+location);
            }
        }
    }

}