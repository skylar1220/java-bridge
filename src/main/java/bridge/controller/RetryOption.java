package bridge.controller;

import java.util.Arrays;

public enum RetryOption {
    RETRY("재시도", "R"), QUIT("종료", "Q");

    private final String optionName;
    private final String userInput;

    RetryOption(String optionName, String userInput) {
        this.optionName = optionName;
        this.userInput = userInput;
    }

    public static RetryOption from(String userInput) {
        return Arrays.stream(values())
                .filter(retryOption -> retryOption.userInput.equals(userInput))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("재시도 여부는 R, Q로 입력해야합니다."));
    }

    public boolean isRetry() {
        return this == RETRY;
    }

    public boolean isQuit() {
        return this == QUIT;
    }
}
