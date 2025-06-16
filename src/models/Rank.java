package models;

// Enum definiujący stopnie wojskowe i ich wartości liczbowe
public enum Rank {
    SZEREGOWY(1),
    KAPRAL(2),
    KAPITAN(3),
    MAJOR(4);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // Zwraca kolejny stopień wojskowy, jeśli możliwy
    public Rank next() {
        return switch (this) {
            case SZEREGOWY -> KAPRAL;
            case KAPRAL -> KAPITAN;
            case KAPITAN -> MAJOR;
            case MAJOR -> MAJOR; // Major to najwyższy stopień
        };
    }
}
