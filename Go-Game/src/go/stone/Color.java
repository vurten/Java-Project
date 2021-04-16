package go.stone;

public enum Color {
    NOIR("Noir"),
    BLANC("Blanc");

    String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
