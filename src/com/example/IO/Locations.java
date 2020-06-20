package com.example.IO;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {

    private static final Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();

    public static void main(String[] args) throws IOException {

        try (BufferedWriter locFile = new BufferedWriter(new FileWriter("locations.txt"));
             BufferedWriter difFile = new BufferedWriter(new FileWriter("direction.txt"))) {
            for (Location location : locations.values()) {
                locFile.write(location.getLocationId() + ", " + location.getDescription() + "\n");
                for (String direction : location.getExits().keySet()) {
                    difFile.write(location.getLocationId() + "," + direction + "," + location.getExits().get(direction) + "\n");


                }
                locFile.flush();
                difFile.close();
            }
        }


    }

    static {

        try {
            FileReader fr = new FileReader("locations_big.txt");
            try (BufferedReader br = new BufferedReader(fr)) {
                String s = null;
                while ((s = br.readLine()) != null) {
                    System.out.println(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Now read exits
        try {
            FileReader fr = new FileReader("directions_big.txt");
            try (BufferedReader br = new BufferedReader(fr)) {
                String s = null;
                while ((s = br.readLine()) != null) {
                    System.out.println(s);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
