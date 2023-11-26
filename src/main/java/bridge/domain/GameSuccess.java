package bridge.domain;

public enum GameSuccess {
    SUCCESS("성공", true), FAIL("실패", false);

    private final String message;

    GameSuccess(String message, boolean hasAllRightMove) {
        this.message = message;
    }

    public static GameSuccess from(boolean finalSuccess) {
        if (finalSuccess) {
            return SUCCESS;
        }
        return FAIL;
    }

    public String getMessage() {
        return message;
    }
}
