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
    private Location location;
    private Double radius;
    private Double distance;
    //------------------------
    private static HashMap<String, InterestPoint> listPoint=new HashMap<String, InterestPoint>();

//----------- - constructors - -----------------------------------------------------------------------------------------

    public InterestPoint(String name, String title, Location location, Double radius, Double distance) {
        this.name = name;
        this.title = title;
        this.location = location;
        this.radius = radius;
        this.distance = distance;
    }
    public InterestPoint(String name, Location location) {
        this.name = name;
        this.title = "Title of " + name;
        this.location = location;
        radius = 8.0;
        distance = 100.0;
    }

//----------- - getters - ----------------------------------------------------------------------------------------------

    //------------------------
    public static HashMap<String,InterestPoint> getListPoint() { return listPoint; }
    public String getTitle() { return title; }
    public Location getLocation() { return location; }

    //------------------------
    public static void setListPoint() { listPoint = listAll(); }

    public static InterestPoint deserialize(Map<String, Object> args) {
        String name = (String)args.get("name");
        String title = (String)args.get("title");
        Location coord = new Location( Bukkit.getWorld((String)args.get("world")), (Double)args.get("x"), (Double)args.get("y"), (Double)args.get("z"));
        Double radius = (Double)args.get("radius");
        Double distance = (Double)args.get("distance");
        return new InterestPoint(name, title, coord, radius, distance);
    }

    private static HashMap<String, InterestPoint> listAll() {
        HashMap<String, InterestPoint> listOfPoint;
        if(listPoint==null){
            listOfPoint = new HashMap<>();
        }else {
            listOfPoint = listPoint;
        }
        FileConfiguration config = InterestPointFiles.getFileInterestPoint();
        Set<String> Keys = config.getKeys(false);
        ArrayList<String> toRemove=new ArrayList<>();
        for(String key:listOfPoint.keySet()){
            if(!Keys.contains(key)){
                toRemove.add(key);
            }
        }
        toRemove.forEach(key->listOfPoint.remove(key));
        for (String key : Keys) {
            if(listOfPoint.containsKey(key)){
                if(config.getConfigurationSection(key)==null && listOfPoint.containsKey(key)){
                    listOfPoint.remove(key);
                }
                else{
                    listOfPoint.get(key).update(deserialize(config.getConfigurationSection(key).getValues(false)));
                }
            }else {
                InterestPoint point = deserialize(config.getConfigurationSection(key).getValues(false));
                listOfPoint.put(point.getName(),point);
            }

        }
        return listOfPoint;
    }

//----------- - setters - ----------------------------------------------------------------------------------------------

    public void setTitle(String title) {
        this.title = title;
        InterestPointFiles.getFileInterestPoint().set(this.getName(), this.serialize());
        InterestPointFiles.saveFileInterestPoint();
    }

    public String getName() { return name; }

    public Double getRadius() { return radius; }

    public void setRadius(Double radius) {
        this.radius = radius;
        InterestPointFiles.getFileInterestPoint().set(this.getName(), this.serialize());
        InterestPointFiles.saveFileInterestPoint();
    }

//----------- - methode - ----------------------------------------------------------------------------------------------

    public Double getDistance() { return distance; }

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

    public void setDistance(Double distance) {
        this.distance = distance;
        InterestPointFiles.getFileInterestPoint().set(this.getName(), this.serialize());
        InterestPointFiles.saveFileInterestPoint();
    }

    public void update(InterestPoint interestPoint){
        this.title=interestPoint.getTitle();
        this.location = interestPoint.getLocation();
        this.radius = interestPoint.getRadius();
        this.distance = interestPoint.getDistance();
    }

}