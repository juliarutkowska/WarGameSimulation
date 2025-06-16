package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Secretary implements Serializable {

    private final List<Report> reports = new ArrayList<>();

    // Główna metoda logująca z automatycznym tagowaniem
    public void log(String message) {
        String tag = taguj(message);
        reports.add(new Report(tag + message)); // Przekazujemy tylko zawartość bez timestampu
    }


    public void showAllReports() {
        for (Report r : reports) {
            System.out.println(r); // timestamp już generuje metoda toString()
        }
    }



    public List<Report> getReports() {
        return reports;
    }

    private String taguj(String msg) {
        if (msg.contains("Bitwa między")) return "[BITWA] ";
        if (msg.contains("zakończył manewry")) return "[MANEWRY] ";
        if (msg.contains("zwiększył doświadczenie") || msg.contains("zyskał doświadczenie")) return "[DOŚWIADCZENIE] ";
        if (msg.contains("stracił doświadczenie")) return "[KARA] ";
        if (msg.contains("awansował")) return "[AWANS] ";
        if (msg.contains("rozstrzelał")) return "[ROZSTRZELANIE] ";
        if (msg.contains("kupił żołnierza")) return "[ZAKUP] ";
        if (msg.contains("Łup:")) return "[ŁUP] ";
        if (msg.contains("Zwyciężył")) return "[WYNIK] ";
        if (msg.contains("nie ma wystarczająco złota")) return "[BŁĄD] ";
        return "";
    }
}
