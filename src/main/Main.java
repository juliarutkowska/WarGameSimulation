package main;

import models.*;
import services.*;

public class Main {
    public static void main(String[] args) {
        Secretary secretary = new Secretary();

        General jan = new General("Jan", 100);
        General pawel = new General("Paweł", 100);

        // przypisujemy sekretarza
        jan.setSecretary(secretary);
        pawel.setSecretary(secretary);

        // kupujemy żołnierzy (z losowymi imionami)
        jan.buySoldier(Rank.SZEREGOWY);
        jan.buySoldier(Rank.KAPRAL);

        pawel.buySoldier(Rank.SZEREGOWY);
        pawel.buySoldier(Rank.KAPITAN);

        // manewry
        TrainingService training = new TrainingService(secretary);
        training.train(jan, jan.getArmy());
        training.train(pawel, pawel.getArmy());

        // bitwa
        BattleService battle = new BattleService(secretary);
        battle.fight(jan, pawel);

        // pokazujemy raport
        secretary.showAllReports();

        // zapis i odczyt
        ArmyPersistence.saveToFile(jan, "jan.dat");
        General janZPliku = ArmyPersistence.loadFromFile("jan.dat");

        System.out.println("Odczytano z pliku: " + janZPliku);
    }
}
