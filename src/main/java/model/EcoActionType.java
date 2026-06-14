package model;

public enum EcoActionType {
    BIKED_TO_WORK("🚲", "Biked to work", 2.1),
    PUBLIC_TRANSPORT("🚌", "Took public transport", 1.5),
    VEGETARIAN_MEAL("🥗", "Ate a vegetarian meal", 1.2),
    RECYCLED("♻️", "Recycled waste", 0.5),
    REUSABLE_BAG("👜", "Used reusable bag", 0.1),
    SHORT_SHOWER("🚿", "Took a short shower", 0.3),
    COMPOSTED("🌱", "Composted food waste", 0.4),
    CAR_FREE_DAY("🚶", "Car-free day", 3.0);

    public final String icon;
    public final String displayName;
    public final double co2Saved;

    EcoActionType(String icon, String displayName, double co2Saved) {
        this.icon = icon;
        this.displayName = displayName;
        this.co2Saved = co2Saved;
    }
}