package mrstraw.titlearea.interestpoint;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import java.util.*;

@SerializableAs("InterestPoint")
public class InterestPoint implements ConfigurationSerializable {

    private final String name;
    private String title;
    private Location coord;
    private int radius;
    private int distance;
    //------------------------
    private static ArrayList<InterestPoint> listPoint;

    public InterestPoint(String name, String title, Location coord, int radius, int distance) {
        this.name = name;
        this.title = title;
        this.coord = coord;
        this.radius = radius;
        this.distance = distance;
    }
    public InterestPoint(String name, Location coord) {
        this.name = name;
        this.title = "Title of " + name;
        this.coord = coord;
        radius = 10;
        distance = 100;
    }

    //------------------------
    public static ArrayList<InterestPoint> getListPoint() { return listPoint; }
    public String getTitle() { return title; }
    public Location getLocation() { return coord; }
    public int getRadius() { return radius; }
    public int getDistance() { return distance; }

    public static InterestPoint deserialize(Map<String, Object> args) {
        String name = (String)args.get("name");
        String title = (String)args.get("title");
        Location coord = new Location( Bukkit.getWorld((String)args.get("world")), (Double)args.get("x"), (Double)args.get("y"), (Double)args.get("z"));
        int radius = (int)args.get("radius");
        int distance = (int)args.get("distance");
        return new InterestPoint(name, title, coord, radius, distance);
    }

    public void setTitle(String title) { this.title = title; }
    public void setCoord(Location coord) { this.coord = coord; }
    public void setRadius(int radius) { this.radius = radius; }
    public void setDistance(int distance) { this.distance = distance; }
    //------------------------
    public static void setListPoint() { listPoint = listAll(); }

    @Override
    public Map<String, Object> serialize() {
        LinkedHashMap point = new LinkedHashMap();
        point.put("name", this.getName());
        point.put("world", this.getLocation().getWorld().getName());
        point.put("title", this.getTitle());
        point.put("x", this.getLocation().getX());
        point.put("y", this.getLocation().getY());
        point.put("z", this.getLocation().getZ());
        point.put("radius", this.getRadius());
        point.put("distance", this.getDistance());
        return point;
    }

    public String getName() { return name; }

    private static ArrayList<InterestPoint> listAll() {
        ArrayList<InterestPoint> listOfPoint = new ArrayList<>();
        FileConfiguration config = InterestPointFiles.getFileInterestPoint();
        Set<String> Keys = config.getKeys(false);

        for (String key : Keys) {
            listOfPoint.add(deserialize((Map)config.getConfigurationSection(key).getValues(false)));
        }
        return listOfPoint;
    }

}