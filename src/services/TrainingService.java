package services;

import models.AbstractSoldier;
import models.General;
import models.Secretary;
import models.Soldier;

import java.util.List;

public class TrainingService {

    private final Secretary secretary;

    public TrainingService(Secretary secretary) {
        this.secretary = secretary;
    }

    // Trenuje wybraną listę żołnierzy
    public void train(General general, List<? extends AbstractSoldier> soldiers)
    {
        if (!general.conductManeuvers(soldiers)) {
            secretary.log("Generał " + general.getName() + " nie ma wystarczająco złota na manewry.");
        }
        // pełne logowanie jest wykonywane wewnątrz conductManeuvers
    }
}
