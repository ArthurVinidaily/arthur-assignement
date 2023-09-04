package com.arthurheliosassignment.packingsolution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class ApproximatePackingOptimization implements CommandLineRunner {

    @Value("${inputItemsToPacked}")
    private String inputItems;

    public static List<String> nextFit(String input) {
        List<String> cartons = new ArrayList<>();
        String[] items = input.split("");
        String currentCarton = "";

        for (String item : items) {
            int cartonSize = currentCarton.chars().map(Character::getNumericValue).sum();
            if (cartonSize + Integer.parseInt(item) <= 10) {
                currentCarton += item;
            } else {
                cartons.add(currentCarton);
                currentCarton = item;
            }
        }

        if (!currentCarton.isEmpty()) {
            cartons.add(currentCarton);
        }
        return cartons;
    }

    public static List<String> firstFit(String input) {
        List<String> cartons = new ArrayList<>();
        String[] items = input.split("");

        for (String item : items) {
            boolean placed = false;
            for (int i = 0; i < cartons.size(); i++) {
                String bin = cartons.get(i);
                int cartonSize = bin.chars().map(Character::getNumericValue).sum();
                if (cartonSize + Integer.parseInt(item) <= 10) {
                    cartons.set(i, bin + item);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                cartons.add(item);
            }
        }

        return cartons;
    }

    public static List<String> bestFit(String input) {
        List<String> cartons = new ArrayList<>();
        String[] items = input.split("");

        for (String item : items) {
            boolean placed = false;

            List<String> sortedCartons = cartons.stream()
                    .sorted(Comparator.comparingInt(ApproximatePackingOptimization::remainingSpace))
                    .collect(Collectors.toList());

            for (String carton : sortedCartons) {
                int cartonSize = remainingSpace(carton);
                if (cartonSize >= Integer.parseInt(item)) {
                    int originalIndex = cartons.indexOf(carton);
                    cartons.set(originalIndex, carton + item);
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                cartons.add(item);
            }
        }

        return cartons;
    }

    public static List<String> worstFit(String input) {
        List<String> cartons = new ArrayList<>();
        String[] items = input.split("");

        for (String item : items) {
            boolean placed = false;

            List<String> sortedCartons = cartons.stream()
                    .sorted(Comparator.comparingInt(carton -> -remainingSpace(carton)))
                    .collect(Collectors.toList());

            for (String carton : sortedCartons) {
                int cartonSize = remainingSpace(carton);
                if (cartonSize >= Integer.parseInt(item)) {
                    int originalIndex = cartons.indexOf(carton);
                    cartons.set(originalIndex, carton + item);
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                cartons.add(item);
            }
        }

        return cartons;
    }

    private static int remainingSpace(String carton) {
        return 10 - carton.chars().map(Character::getNumericValue).sum();
    }

    @Override
    public void run(String... args) {
        if (isValidNumber(inputItems)) {
            List<String> nextFitPacking = ApproximatePackingOptimization.nextFit(inputItems);
            List<String> firstFitPacking = ApproximatePackingOptimization.firstFit(inputItems);
            List<String> bestFitPacking = ApproximatePackingOptimization.bestFit(inputItems);
            List<String> worstFitPacking = ApproximatePackingOptimization.worstFit(inputItems);

            System.out.println("\nInput Items: " + inputItems);

            // next fit
            System.out.println("\nNext Fit: " + String.join("/", nextFitPacking));
            System.out.println("Total Cartons Used: " + nextFitPacking.size());

            // first fit
            System.out.println("\nFirst Fit: " + String.join("/", firstFitPacking));
            System.out.println("Total Cartons Used: " + firstFitPacking.size());

            // best fit
            System.out.println("\nBest Fit: " + String.join("/", bestFitPacking));
            System.out.println("Total Cartons Used: " + bestFitPacking.size());

            // worst fit
            System.out.println("\nWorst Fit: " + String.join("/", worstFitPacking));
            System.out.println("Total Cartons Used: " + worstFitPacking.size());

            System.out.println("\n");
        }
    }

    private static boolean isValidNumber(String input) {
        if (input.matches("\\d+")) {
            return true;
        } else if (input.equals("@inputItemsToPacked@")) {
            System.out.println("\n\nThe inputItemsToPacked should be defined for packing...\n\n");
        } else {
            System.out.println("\n\nThe inputItemsToPacked should be numbers for packing: " + input + "\n\n");
        }
        return false;
    }
}
