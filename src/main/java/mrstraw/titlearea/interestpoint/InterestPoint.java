package mrstraw.titlearea.interestpoint;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import java.util.*;

@SerializableAs("InterestPoint")
public class InterestPoint implements ConfigurationSerializable {

//----------- - Attributs - --------------------------------------------------------------------------------------------

    private final String name;
    private String title;
    private Location coord;
    private int radius;
    private int distance;
    //------------------------
    private static HashMap<String, InterestPoint> listPoint;

//----------- - constructors - -----------------------------------------------------------------------------------------

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
        radius = 8;
        distance = 100;
    }

//----------- - getters - ----------------------------------------------------------------------------------------------

    //------------------------
    public static HashMap<String,InterestPoint> getListPoint() { return listPoint; }
    public String getTitle() { return title; }
    public Location getLocation() { return coord; }
    public int getRadius() { return radius; }
    public int getDistance() { return distance; }

    //------------------------
    public static void setListPoint() { listPoint = listAll(); }

//----------- - setters - ----------------------------------------------------------------------------------------------

    public void setTitle(String title) {
        this.title = title;
        InterestPointFiles.getFileInterestPoint().set(this.getName(), this.serialize());
        InterestPointFiles.saveFileInterestPoint();
    }
    public void setRadius(int radius) {
        this.radius = radius;
        InterestPointFiles.getFileInterestPoint().set(this.getName(), this.serialize());
        InterestPointFiles.saveFileInterestPoint();
    }

    private static HashMap<String, InterestPoint> listAll() {
        HashMap<String, InterestPoint> listOfPoint = new HashMap<>();
        FileConfiguration config = InterestPointFiles.getFileInterestPoint();
        Set<String> Keys = config.getKeys(false);

        for (String key : Keys) {
            InterestPoint point = deserialize(config.getConfigurationSection(key).getValues(false));
            listOfPoint.put(point.getName(),point);
        }
        return listOfPoint;
    }

//----------- - methode - ----------------------------------------------------------------------------------------------

    public static InterestPoint deserialize(Map<String, Object> args) {
        String name = (String)args.get("name");
        String title = (String)args.get("title");
        Location coord = new Location( Bukkit.getWorld((String)args.get("world")), (Double)args.get("x"), (Double)args.get("y"), (Double)args.get("z"));
        int radius = (int)args.get("radius");
        int distance = (int)args.get("distance");
        return new InterestPoint(name, title, coord, radius, distance);
    }

    @Override
    public Map<String, Object> serialize() {
        double X = Math.floor(this.getLocation().getX()) + 0.5;
        double Y = Math.floor(this.getLocation().getY()) - 0.5;
        double Z = Math.floor(this.getLocation().getZ()) + 0.5;
        LinkedHashMap point = new LinkedHashMap();
        point.put("name", this.getName());
        point.put("world", this.getLocation().getWorld().getName());
        point.put("title", this.getTitle());
        point.put("x", X);
        point.put("y", Y);
        point.put("z", Z);
        point.put("radius", this.getRadius());
        point.put("distance", this.getDistance());
        return point;
    }

    public void setDistance(int distance) {
        this.distance = distance;
        InterestPointFiles.getFileInterestPoint().set(this.getName(), this.serialize());
        InterestPointFiles.saveFileInterestPoint();
    }

    public String getName() { return name; }

}