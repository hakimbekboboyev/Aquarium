package org.example;

import java.util.Random;

public class Fish extends Thread {
    private static final Random random = new Random();
    private int x_Coordinate;
    private int y_Coordinate;
    private static int idCounter = 1;
    private final int id;
    private final String gender;
    private final int lifeSpan;
    private final long fatherId; // Ota baliqning ID si
    private final long motherId; // ona baliqning ID si
    private final Aquarium aquarium;

    public void setX_Coordinate(int x_Coordinate) {
        this.x_Coordinate = x_Coordinate;
    }

    public void setY_Coordinate(int y_Coordinate) {
        this.y_Coordinate = y_Coordinate;
    }

    public Fish(String gender, int lifeSpan, int x_Coordinate, int y_Coordinate, long fatherId, long motherId, Aquarium aquarium) {
        this.id = idCounter++;
        this.gender = gender;
        this.x_Coordinate = x_Coordinate;
        this.y_Coordinate = y_Coordinate;
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
                (motherId > 0 || fatherId > 0 ? "Ota-onasi: #" + fatherId + " va #" + motherId : "Ota-onasi yo'q. xy(" +
                        this.x_Coordinate + ", " + y_Coordinate + ")"
                ));

        try {
            for (int i = 0; i < lifeSpan; i++) {
                Thread.sleep(50);

//                if (random.nextInt(3) == 0) { // 33% ehtimol bilan harakat qiladi
                    aquarium.fishInteraction(this);
//                }
                aquarium.movementFish();
            }
        } catch (InterruptedException e) {
            System.out.println("Baliq #" + id + " o'ldi.");

        }
        aquarium.removeFish(this);

    }

    public int getX_Coordinate(){
        return x_Coordinate;
    }
    public int getY_Coordinate(){
        return y_Coordinate;
    }
}
