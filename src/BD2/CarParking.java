package BD2;

import java.io.*;
import java.util.*;



public class CarParking {
    public static final int MAX_CAPACITY = 10;
    public static Stack<Car> garage = new Stack<>();
    public static List<String> results = new ArrayList<>();

    public static void main(String[] args) {
        List<String[]> commands = input("src/BD2/cars.txt");
        process(commands);
        output(results);
    }

    public static List<String[]> input(String filename) {
        List<String[]> commands = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    commands.add(parts);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return commands;
    }

    public static void process(List<String[]> commands) {
        for (String[] command : commands) {
            String action = command[0];
            String plate = command[1];

            if (action.equalsIgnoreCase("A")) {
                handleArrival(plate);
            } else if (action.equalsIgnoreCase("D")) {
                handleDeparture(plate);
            }
        }
    }

    static void handleArrival(String plate) {
        if (garage.size() >= MAX_CAPACITY) {
            results.add("Arrival " + plate + " -> Garage full, this car cannot enter.");
        } else {
            garage.push(new Car(plate));
            results.add("Arrival " + plate + " -> There is room.");
        }
    }

    public static void handleDeparture(String plate) {
        Stack<Car> tempStack = new Stack<>();
        boolean found = false;
        int movedCars = 0;

        while (!garage.isEmpty()) {
            Car topCar = garage.pop();
            if (topCar.licensePlate.equals(plate)) {
                found = true;
                break;
            } else {
                tempStack.push(topCar);
                movedCars++;
            }
        }

        if (!found) {
            results.add("Departure " + plate + " -> This car not in the garage.");
        } else {
            results.add("Departure " + plate + " -> " + movedCars + " cars moved out.");
        }

        while (!tempStack.isEmpty()) {
            garage.push(tempStack.pop());
        }
    }

    public static void output(List<String> messages) {
        for (String msg : messages) {
            System.out.println(msg);
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("src/BD2/output.txt"))) {
            for (String msg : messages) {
                writer.println(msg);
            }
        } catch (IOException e) {
            System.out.println("Error writing output: " + e.getMessage());
        }
    }
}
