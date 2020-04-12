package mrstraw.titlearea.interestpoint;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;


public class InterestPointEvent implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location PlayerLoc = event.getPlayer().getLocation();
        ArrayList<InterestPoint> listPoint = InterestPoint.getListPoint();
        for (InterestPoint point : listPoint) {
            if (lookingAt(PlayerLoc, point)) {
                event.getPlayer().sendMessage("Tu regardes " + point.getTitle());
            }
        }
    }

    private boolean lookingAt(Location playerLoc, InterestPoint point){
        double Zp = playerLoc.getZ();
        double Xp = playerLoc.getX();
        Location pointLoc = point.getLocation();
        double Zj = pointLoc.getZ();
        double Xj = pointLoc.getX();
        double A = playerLoc.getYaw();
        double D = playerLoc.distance(pointLoc);
        double r = point.getRadius();

        double playerPointAngle = Math.acos(((Zp - Zj)*Math.cos(A) - (Xp - Xj)*Math.sin(A))/D);
        double maxAngle = Math.asin(r/Math.sqrt(Math.pow(r, 2) + Math.pow(D, 2)));

        boolean angleCondition = (playerPointAngle < maxAngle) && (-maxAngle < playerPointAngle);
        boolean distanceCondition = D < point.getDistance();

        return angleCondition && distanceCondition;
    }
}
