package mrstraw.titlearea.interestpoint;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import java.util.ArrayList;

public class InterestPointEvent implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location PlayerLoc = event.getPlayer().getLocation();
        ArrayList<InterestPoint> listPoint = InterestPoint.getListPoint();
        for (InterestPoint point : listPoint) {
            if (lookingAt(PlayerLoc, point)) {
                event.getPlayer().sendMessage("Tu regardes '" + point.getTitle() + "'.");
            }
        }
    }

    private boolean lookingAt(Location playerLoc, InterestPoint point){
        Location pointLoc = point.getLocation();
        double r = point.getRadius();

        Vector playerDirection = playerLoc.getDirection();
        playerDirection.normalize();
        Vector delta = pointLoc.toVector().add(playerLoc.toVector().multiply(-1));
        double D = delta.length();
        delta.normalize();

        double playerPointAngle = Math.acos(delta.dot(playerDirection));
        double maxAngle = Math.asin(r/Math.sqrt(Math.pow(r, 2) + Math.pow(D, 2)));

        boolean angleCondition = playerPointAngle < maxAngle;
        boolean distanceCondition = D < point.getDistance();

        return angleCondition && distanceCondition;
    }
}
