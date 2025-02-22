package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
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
}