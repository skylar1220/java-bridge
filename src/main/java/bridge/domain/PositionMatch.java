package bridge.domain;

public enum PositionMatch {
    MATCH("O", true), NOT_MATCHED("X", false);

    private final String mark;

    PositionMatch(String mark, boolean matchPosition) {
        this.mark = mark;
    }

    public static PositionMatch from(boolean positionMatch) {
        if (positionMatch) {
            return MATCH;
        }
        return NOT_MATCHED;
    }

    public boolean isNotMatch() {
        return this == NOT_MATCHED;
    }

    public String getMark() {
        return mark;
    }
}
