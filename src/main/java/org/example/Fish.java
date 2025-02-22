package org.example;

import java.util.Random;

public class Fish extends Thread {
    private static final Random random = new Random();
    private static int idCounter = 1;
    private final int id;
    private final String gender;
    private final int lifeSpan;
    private final long fatherId; // Ota baliqning ID si
    private final long motherId; // ona baliqning ID si
    private final Aquarium aquarium;

    public Fish(String gender, int lifeSpan, long fatherId, long motherId, Aquarium aquarium) {
        this.id = idCounter++;
        this.gender = gender;
        this.lifeSpan = lifeSpan;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.aquarium = aquarium;
    }

    public long getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public void run() {
        System.out.println("Baliq #" + id + " (" + gender + ") tug'ildi, yashash muddati: " + lifeSpan + "s. " +
                (motherId > 0 || fatherId > 0 ? "Ota-onasi: #" + fatherId + " va #" + motherId : "Ota-onasi yo'q."));

        try {
            for (int i = 0; i < lifeSpan; i++) {
                Thread.sleep(1000);

                if (random.nextInt(3) == 0) { // 33% ehtimol bilan harakat qiladi
                    aquarium.fishInteraction(this);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Baliq #" + id + " o'ldi.");

        }
        aquarium.removeFish(this);

    }

    public long getFatherId() {
        return fatherId;
    }

    public long getMotherId() {
        return motherId;
    }
}
