public enum Terrain {
    HILL(4),
    PLAINS(4),
    FOREST(4);
    int bonus;

    Terrain(int bonus) {
        this.bonus = bonus;
    }
}
