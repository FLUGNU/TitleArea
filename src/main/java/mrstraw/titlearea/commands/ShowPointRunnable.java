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
        for(Double theta=Math.random()*2*Math.PI/32; theta < 2 * Math.PI ; theta += 2*Math.PI/32){
            for(Double phi = Math.random()*2*Math.PI/32 ; phi < 2 * Math.PI ; phi += 2*Math.PI/32){

                world.spawnParticle(Particle.FLAME
                        ,location.getX()+distance*Math.cos(theta)*Math.cos(phi)
                        ,location.getY()+distance*Math.sin(theta)
                        ,location.getZ()+distance*Math.cos(theta)*Math.sin(phi)
                        ,1,0,0,0,0);
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