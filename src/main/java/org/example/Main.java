package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

       //calculateFish();
       calculateAuto();
    }

    public static void calculateAuto() {
        Random random = new Random();
        int aquariumSize = 7 + random.nextInt(5); // 6-11 sig'imli akvarium
        Aquarium aquarium = new Aquarium(aquariumSize);

        int numMale = 1 + random.nextInt(3); // 1-3 erkak baliq
        int numFemale = 1 + random.nextInt(3); // 1-3 urg'ochi baliq

        System.out.println("Akvarium hajmi: " + aquariumSize);
        System.out.println("Akvariumda " + numMale + " ta erkak va " + numFemale + " ta urg'ochi baliq bor.");

        for (int i = 0; i < numMale; i++) {
            aquarium.addFish(new Fish("Erkak", 5 + random.nextInt(10), 0,0, aquarium));
        }
        for (int i = 0; i < numFemale; i++) {
            aquarium.addFish(new Fish("Urg'ochi", 5 + random.nextInt(10), 0, 0, aquarium));
        }
    }

    public static void calculateFish() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Akvarium hajmini kiriting (min 6, max 11): ");
        int aquariumSize = scanner.nextInt();

        if (aquariumSize < 6 || aquariumSize > 11) {
            System.out.println("Xato! Akvarium hajmi 6 dan 11 gacha bo'lishi kerak.");
            return;
        }

        Aquarium aquarium = new Aquarium(aquariumSize);

        System.out.print("Nechta erkak baliq qo'shmoqchisiz? (1-3): ");
        int numMale = scanner.nextInt();

        System.out.print("Nechta urg‘ochi baliq qo'shmoqchisiz? (1-3): ");
        int numFemale = scanner.nextInt();

        if (numMale < 1 || numMale > 3 || numFemale < 1 || numFemale > 3) {
            System.out.println("Xato! Erkak va urg‘ochi baliqlar soni 1-3 orasida bo‘lishi kerak.");
            return;
        }

        System.out.println("Akvarium hajmi: " + aquariumSize);
        System.out.println("Akvariumda " + numMale + " ta erkak va " + numFemale + " ta urg‘ochi baliq bor.");

        for (int i = 0; i < numMale; i++) {
            aquarium.addFish(new Fish("Erkak", 5 + random.nextInt(10), 0, 0, aquarium));
        }

        for (int i = 0; i < numFemale; i++) {
            aquarium.addFish(new Fish("Urg‘ochi", 5 + random.nextInt(10), 0, 0, aquarium));
        }

        scanner.close();
    }

}