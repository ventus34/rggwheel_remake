package ventus.rggwheel.model;

public enum ItemEnum {
    Potion("Potion"),
    Hints("Hint");

    final String description;

    ItemEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
