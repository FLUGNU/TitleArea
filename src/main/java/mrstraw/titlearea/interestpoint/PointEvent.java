package mrstraw.titlearea.interestpoint;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PointEvent implements Listener {
    private static HashMap<UUID, String> playerIndexPoint = new HashMap<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        Location playerLoc = p.getEyeLocation();
        HashMap<String, InterestPoint> listAllPoint = InterestPoint.getListPoint();
        ArrayList<InterestPoint> aroundPoint = new ArrayList<>();

        // --------- - Partie affichage distance - --------------------
        for (InterestPoint iPoint : listAllPoint.values()) {
            if (playerLookPoint(playerLoc,iPoint)) {
                aroundPoint.add(iPoint);
            }
        }
        // Si il ne voie pas de point
        if (aroundPoint.size() == 0) {
            // Mais qu'il regardais un point, on efface le title
            if (playerIndexPoint.containsKey(p.getUniqueId()) && !playerIndexPoint.get(p.getUniqueId()).equals("")) {
                p.sendTitle("","",0,0,0);
            }
            playerIndexPoint.put(p.getUniqueId(),"");
        }
        // Si il voie un/des points
        else {
            // Si il voie un seul point
            InterestPoint pointToPrint = aroundPoint.get(0);
            // Si voie plusieurs points
            if (aroundPoint.size() != 1) {
                double smallest = Double.MAX_VALUE;
                for (InterestPoint iPoint : aroundPoint) {
                    if (iPoint.getDistance() < smallest) {
                        smallest = iPoint.getDistance();
                        pointToPrint = iPoint;
                    }
                }
            }
            // Si le point à afficher est diff du precedent, on l'affiche
            if (playerIndexPoint.containsKey(p.getUniqueId()) && !playerIndexPoint.get(p.getUniqueId()).equals(pointToPrint.getName())) {
                p.sendTitle("", pointToPrint.getTitle(), 3,30,15);
            }
            //on met à jour le point observé
            playerIndexPoint.put(p.getUniqueId(),pointToPrint.getName());
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        playerIndexPoint.remove(event.getPlayer().getUniqueId());
    }

    private boolean playerLookPoint(Location playerLoc, InterestPoint point) {
        //---------- - Variables - -------------------------------------------------------
        Location pointLoc = point.getLocation();
        double pointRadius = point.getRadius();
        double pointDistance = point.getDistance();
        //---------- - Calculs - ---------------------------------------------------------
        Vector playerDirection = playerLoc.getDirection().normalize();
        Vector delta = pointLoc.toVector().add(playerLoc.toVector().multiply(-1));
        double D = delta.length();
        delta.normalize();
        double playerPointAngle = Math.acos(delta.dot(playerDirection));
        double maxAngle = Math.asin(pointRadius/Math.sqrt(Math.pow(pointRadius, 2) + Math.pow(D, 2)));
        ///--------- - Condition - -------------------------------------------------------
        boolean angleCondition = playerPointAngle < maxAngle;
        boolean distanceCondition = (D < pointDistance) && (D > pointRadius);
        boolean sameWorld = playerLoc.getWorld() == pointLoc.getWorld();
        //---------- - return - ----------------------------------------------------------
        return angleCondition && distanceCondition && sameWorld;
    }

    private boolean playerInPoint(Location playerLoc, InterestPoint point) {
        //---------- - Variables - -------------------------------------------------------
        Location pointLoc = point.getLocation();
        double pointRadius = point.getRadius();
        //---------- - Calculs - ---------------------------------------------------------
        Vector delta = pointLoc.toVector().add(playerLoc.toVector().multiply(-1));
        double D = delta.length();
        ///--------- - Condition - -------------------------------------------------------
        boolean radiusCondition = D < pointRadius;
        boolean sameWorld = playerLoc.getWorld() == pointLoc.getWorld();
        //---------- - return - ----------------------------------------------------------
        return radiusCondition && sameWorld;
    }

}
