package org.example;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Aquarium {
    private final List<Fish> fishes = new CopyOnWriteArrayList<>();
    private static final Random random = new Random();
    private final int capacity;
    private boolean isRunning = true;

    public Aquarium(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void addFish(Fish fish) {
        if (!isRunning) return;
        if (fishes.size() >= capacity) {
            System.out.println("Akvarium to'ldi! Akvarium o'chirilyapti...");
            System.out.println("Akvarium o'chirildi...");
            shutdown();
            return;
        }
        fishes.add(fish);
        fish.start();
    }

    public synchronized void removeFish(Fish fish) {
        fishes.remove(fish);
        System.out.println("Baliq #" + fish.getId() + " o'ldi va akvariumdan olib tashlandi.");
        if (fishes.isEmpty()) {
            shutdown();
        }
    }

    public synchronized void fishInteraction(Fish fish) throws InterruptedException {
        for (Fish otherFish : fishes) {
            if (fish != otherFish && !fish.getGender().equals(otherFish.getGender())) {
                System.out.println("Baliq #" + fish.getId() + " va Baliq #" + otherFish.getId() + " uchrashdi!");
                spawnNewFish(fish.getId(), otherFish.getId());
                otherFish.join(10);
                break;
            }
        }
    }

    private void spawnNewFish(long fishId, long otherFishId) {
        if (!isRunning) return;
        String gender = random.nextBoolean() ? "Erkak" : "Urg'ochi";
        int lifeSpan = 5 + random.nextInt(10); // 5-15 soniya
        Fish newFish = new Fish(gender, lifeSpan, fishId, otherFishId, this);
        addFish(newFish);
    }

    public void shutdown() {
        isRunning = false;
        for (Fish fish : fishes) {
            fish.interrupt();
        }
        fishes.clear();



    }
}
