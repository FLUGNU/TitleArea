package mrstraw.titlearea.interestpoint;

import javafx.util.Pair;
import org.bukkit.Bukkit;
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

import static org.bukkit.Bukkit.getServer;

public class PointEvent implements Listener {
    private static HashMap<UUID, ArrayList<String>> playerIndexPoint = setPlayerIndexPoint();

    private static HashMap<UUID, ArrayList<String>> setPlayerIndexPoint() {
        HashMap<UUID, ArrayList<String>> hashMapPlayer = new HashMap<>();
        ArrayList<Player> allPlayer = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (Player iPlayer : allPlayer) {
            ArrayList<String> diRa =  new ArrayList<>();
            diRa.add(""); // Distance initialize
            diRa.add(""); // Radius initialize
            hashMapPlayer.put(iPlayer.getUniqueId(), diRa);
        }
        return hashMapPlayer;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        Player p = event.getPlayer();
        ArrayList<String> playerTampon = playerIndexPoint.get(p.getUniqueId()); // 1:distance, 2:Radius

        Location playerLoc = p.getEyeLocation();
        HashMap<String, InterestPoint> listAllPoint = InterestPoint.getListPoint();
        ArrayList<InterestPoint> aroundDistancePoint = new ArrayList<>();
        ArrayList<InterestPoint> aroundRadiusPoint = new ArrayList<>();

    //------------ - Partie affichage distance - -----------------------------------------------------------------------
        for (InterestPoint iPoint : listAllPoint.values()) {
            if (playerLookPoint(playerLoc,iPoint)) {
                aroundDistancePoint.add(iPoint);
            }
        }
        // Si il ne voie pas de point
        if (aroundDistancePoint.size() == 0) {
            // Mais qu'il regardais un point, on efface le title
            if (!playerTampon.get(0).equals("")) {
                p.sendTitle("","",0,0,0);
            }
            playerTampon.set(0,"");
        }
        // Si il voie un/des points
        else {
            // Si il voie un seul point
            InterestPoint pointToPrint = aroundDistancePoint.get(0);
            // Si voie plusieurs points
            if (aroundDistancePoint.size() != 1) {
                double smallest = Double.MAX_VALUE;
                for (InterestPoint iPoint : aroundDistancePoint) {
                    if (iPoint.getDistance() < smallest) {
                        smallest = iPoint.getDistance();
                        pointToPrint = iPoint;
                    }
                }
            }
            // Si le point à afficher est diff du precedent, on l'affiche
            if (!playerTampon.get(0).equals(pointToPrint.getName())) {
                p.sendTitle("", pointToPrint.getTitle(), 3,30,15);
            }
            //on met à jour le point observé
            playerTampon.set(0,pointToPrint.getName());
        }

    //------------ - Partie affichage radius - -------------------------------------------------------------------------

        for (InterestPoint iPoint : listAllPoint.values()) {
            if (playerInPoint(playerLoc,iPoint)) {
                aroundRadiusPoint.add(iPoint);
            }
        }
        // Si le joueur n'est pas dans un radius
        if (aroundRadiusPoint.size() == 0) {
            playerTampon.set(1, "");
        }
        else {
            InterestPoint pointToPrint = aroundRadiusPoint.get(0);
            double smallest = Double.MAX_VALUE;
            for (InterestPoint iPoint : aroundRadiusPoint) {
                if (iPoint.getRadius() < smallest) {
                    smallest = iPoint.getRadius();
                    pointToPrint = iPoint;
                }
            }
            // Si le point à afficher est diff du precedent, on l'affiche
            if (!playerTampon.get(1).equals(pointToPrint.getName())) {
                p.sendTitle(pointToPrint.getTitle(), "", 3,30,15);
            }
            //on met à jour le point observé
            playerTampon.set(1,pointToPrint.getName());
        }

    //------------ - Réinjection de l'arrayList dans la hashMap- -------------------------------------------------------

        playerIndexPoint.put(p.getUniqueId(), playerTampon);

    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        playerIndexPoint.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent event) {
        ArrayList<String> diRa =  new ArrayList<>();
        diRa.add(""); // Distance initialize
        diRa.add(""); // Radius initialize
        playerIndexPoint.put(event.getPlayer().getUniqueId(), diRa);
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