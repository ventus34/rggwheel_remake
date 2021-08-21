package ventus.rggwheel.model;

public enum RetroBoyColorEnum {
    BANANA_YELLOW("#ebcb3a"),
    LEMON_YELLOW("#fcdc15"),
    GREEN("#7edf16"),
    PISTACHIO("#45d98d"),
    AZURE("#1797c6"),
    CERULEAN("#3162af"),
    PURPLE("#6029a8"),
    CRISMON("#bf263a"),
    RED("#f1151f"),
    ECRIE("#e5dfd1");
    final String color;

     RetroBoyColorEnum(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
