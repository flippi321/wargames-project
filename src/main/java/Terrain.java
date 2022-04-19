public enum Terrain {
    HILL(2),
    PLAINS(2),
    FOREST(2);
    int bonus;

    Terrain(int bonus) {
        this.bonus = bonus;
    }
}
