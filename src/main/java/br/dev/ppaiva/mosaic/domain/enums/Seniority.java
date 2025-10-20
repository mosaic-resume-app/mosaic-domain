package br.dev.ppaiva.mosaic.domain.enums;

public enum Seniority {
    INTERN("Intern", 0),
    JUNIOR("Junior", 1),
    MID_LEVEL("Mid-Level", 2),
    SENIOR("Senior", 3),
    SPECIALIST("Specialist / Staff", 4);

    private final String displayName;
    private final int level;

    Seniority(String displayName, int level) {
        this.displayName = displayName;
        this.level = level;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getLevel() {
        return level;
    }
    
    /**
     * Checks if this seniority is higher than or equal to another.
     * @param other The seniority to compare against.
     * @return true if the level is greater than or equal, false otherwise.
     */
    public boolean isSeniorOrHigher(Seniority other) {
        return this.level >= other.getLevel();
    }
}