package org.land;

import java.util.Scanner;

public class FertileLandArea {
    public static void main(String[] args) {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        land.parseBarrenLands(getBarrenLandInputString());
        System.out.println(land.getFertileLandAreas());
    }

    public static String getBarrenLandInputString() {
        System.out.println("Enter coordinates for barren land rectangles ");
        try( Scanner scanner = new Scanner(System.in) ) {
            String input = scanner.nextLine();
            return input;
        }
    }
}
