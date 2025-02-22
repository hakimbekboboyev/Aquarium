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
            if (fish != otherFish && !fish.getGender().equals(otherFish.getGender()) &&
                    fish.getX_Coordinate()==otherFish.getX_Coordinate() &&
                    fish.getY_Coordinate()==otherFish.getY_Coordinate()
            ) {
                System.out.println("Baliq #" + fish.getId() + " va Baliq #" + otherFish.getId() + " uchrashdi!" +
                        " xy("+fish.getX_Coordinate()+","+fish.getY_Coordinate()+")");
                spawnNewFish(fish.getId(), otherFish.getId(),
                        fish.getX_Coordinate(),
                        fish.getY_Coordinate());

                break;
            }
        }
    }

    private void spawnNewFish(long fishId, long otherFishId, int x1, int y1 ) {
        if (!isRunning) return;
        String gender = random.nextBoolean() ? "Erkak" : "Urg'ochi";
        int lifeSpan = 5 + random.nextInt(100); // 5-15 soniya
        Fish newFish = new Fish(gender, lifeSpan, x1, y1, fishId, otherFishId, this);
        addFish(newFish);
    }


    public synchronized void movementFish() throws InterruptedException {
        for (Fish fish : fishes) {
            Thread.sleep(1);
            int random_x = 1+random.nextInt(capacity);
            int random_y = 1+random.nextInt(capacity);
            fish.setX_Coordinate(random_x);
            fish.setY_Coordinate(random_y);


        }
    }

    public void shutdown() {
        isRunning = false;
        for (Fish fish : fishes) {
            fish.interrupt();
        }
        fishes.clear();



    }
}
