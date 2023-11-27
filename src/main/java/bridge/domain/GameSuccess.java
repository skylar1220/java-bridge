package bridge.domain;

public enum GameSuccess {
    SUCCESS("성공", true), FAIL("실패", false);

    private final String message;
    private final boolean finalSuccess;

    GameSuccess(String message, boolean finalSuccess) {
        this.message = message;
        this.finalSuccess = finalSuccess;
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
