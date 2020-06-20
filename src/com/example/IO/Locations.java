package com.example.IO;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {

    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    public static void main(String[] args) throws IOException {
//        FileWriter locFile = null;
//        try {
//            locFile = new FileWriter("locations.txt");
//            for (Location location : locations.values()) {
//                locFile.write(location.getLocationId() + " " + location.getDescription() + "\n");
//                throw new IOException("test exception thrown while writing");
//            }
//        } finally {
//            System.out.println("In finally block");
//            if (locFile != null) {
//                System.out.println("Attempting to close locFile");
//                locFile.close();
//            }
//        }
//
        try (FileWriter locFile = new FileWriter("locations.txt");
             FileWriter difFile = new FileWriter("direction.txt")) {
            for (Location location : locations.values()) {
                locFile.write(location.getLocationId() + ", " + location.getDescription() + "\n");
                for (String direction : location.getExits().keySet()) {
                    difFile.write(location.getLocationId() + "," + direction + "," + location.getExits().get(direction) + "\n");

                }
            }
        }

    }
    static {

        try {
            try (Scanner scan = new Scanner(new BufferedReader(new FileReader("locations_big.txt")))) {
                scan.useDelimiter(",");
                while (scan.hasNextLine()) {
                    int location = scan.nextInt();
                    scan.skip(scan.delimiter());
                    String description = scan.nextLine();
                    System.out.println("Imported loc: " + location + ": " + description);
                    Map<String, Integer> tempExit = new HashMap<>();
                    locations.put(location, new Location(location, description, tempExit));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Now read exits
        try (Scanner scan = new Scanner(new FileReader("directions_big.txt"))){
            scan.useDelimiter(",");
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                String [] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data [1];
                int destination = Integer.parseInt(data [2]);
                System.out.println(loc + ": " + direction + ": " + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        }

//
//        Map<String, Integer> tempExit = new HashMap<String, Integer>();
//
//        locations.put(0, new
//
//                Location(0, "You are sitting in front of a computer learning Java", tempExit));
//
//
//        tempExit.put("W", 2);
//        tempExit.put("E", 3);
//        tempExit.put("S", 5);
//        tempExit.put("N", 2);
//        locations.put(1, new
//
//                Location(1, "You are standing at the end of a road before a small brick building", tempExit));
//
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("N", 5);
//        locations.put(2, new
//
//                Location(2, "You are at the top of a hill", tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("W", 1);
//        locations.put(3, new
//
//                Location(3, "You are inside a building, a well house for a small spring", tempExit));
//
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("N", 1);
//        tempExit.put("W", 2);
//        locations.put(4, new
//
//                Location(4, "You are in a valley besides stream", tempExit));
//
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("S", 1);
//        tempExit.put("W", 2);
//        locations.put(5, new
//
//                Location(5, "You are in the forest", tempExit));


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
