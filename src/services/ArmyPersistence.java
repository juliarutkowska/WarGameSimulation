package services;

import models.General;

import java.io.*;

public class ArmyPersistence {

    // Zapisuje obiekt generała do pliku
    public static void saveToFile(General general, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(general);
        } catch (IOException e) {
            System.out.println("Błąd zapisu: " + e.getMessage());
        }
    }

    // Wczytuje generała z pliku
    public static General loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (General) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Błąd odczytu: " + e.getMessage());
            return null;
        }
    }
}
