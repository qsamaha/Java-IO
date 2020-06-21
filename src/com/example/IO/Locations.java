package com.example.IO;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {

    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();

    public static void main(String[] args) throws IOException {

        try (BufferedWriter locFile = new BufferedWriter(new FileWriter("locations.txt"));
             BufferedWriter difFile = new BufferedWriter(new FileWriter("direction.txt"))) {
            for (Location location : locations.values()) {
                locFile.write(location.getLocationId() + ", " + location.getDescription() + "\n");
                for (String direction : location.getExits().keySet()) {
                    if(!direction.equalsIgnoreCase( "0")) {
                        difFile.write(location.getLocationId() + "," + direction + "," + location.getExits().get(direction) + "\n");
                    }
                }
            }
        }

        System.out.println(locations.size());
    }

    static {

        try (Scanner scan = new Scanner(new BufferedReader(new FileReader("locations_big.txt")))) {
            scan.useDelimiter(",");
            while (scan.hasNextLine()) {
                int loc = scan.nextInt();
                scan.skip(scan.delimiter());
                String description = scan.nextLine();
                System.out.println("Imported Location: " + loc + ", " + description);
                Map<String, Integer> tempExit = new HashMap<>();
                locations.put(loc, new Location(loc, description, tempExit));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        //Now read exits
        try (BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))) {
            String input;
            while((input = dirFile.readLine()) != null) {
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);

                System.out.println(loc + ": " + direction + ": " + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
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
