package com.example.purva.bbvaassignment.data;

import java.util.Comparator;

/**
 * Created by purva on 4/22/18.
 */

public class Location {


    String addr, longitude, latitude, id, name,icon, rating;

    public Location(String addr, String longitude, String latitude, String id, String name, String icon, String rating) {
        this.addr = addr;
        this.longitude = longitude;
        this.latitude = latitude;
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.rating = rating;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public static Comparator<Location> locationDistanceComparator = new Comparator<Location>() {

        float[] result;
        public int compare(Location s1, Location s2) {
            Double locationLatitude1 = Double.valueOf(s1.getLatitude());
            Double locationLongitude1 = Double.valueOf(s1.getLongitude());
            Double locationLatitude2 = Double.valueOf(s2.getLatitude());
            Double locationLongitude2 = Double.valueOf(s2.getLongitude());

            android.location.Location location1 = new android.location.Location("point A");
            location1.setLatitude(locationLatitude1);
            location1.setLongitude(locationLongitude1);
            android.location.Location location2 = new android.location.Location("point B");
            location1.setLatitude(locationLatitude2);
            location1.setLongitude(locationLongitude2);

            float distance = location1.distanceTo(location2);
            //ascending order
            return (int) distance;
        }};
}
