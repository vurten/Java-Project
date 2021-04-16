package go.stone;

public enum Alphabet {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    J(9);

    private final int value;

    Alphabet(int value) {
        this.value = value;
    }

    public int getEnumValue() {
        return value;
    }

}

