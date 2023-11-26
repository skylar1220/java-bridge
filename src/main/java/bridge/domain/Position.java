package bridge.domain;

import java.util.Arrays;

public enum Position {
    UP("U", 1),
    DOWN("D", 0);

    private final String positionName;
    private final int number;

    Position(String positionName, int number) {
        this.positionName = positionName;
        this.number = number;
    }

    public static Position fromNumber(int positionNumber) {
        return Arrays.stream(values())
                .filter(position -> position.number == positionNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("이동 위치에 적용할 숫자는 %d, %d 중에 하나여야합니다.", UP.number, DOWN.number)));
    }

    public static Position fromPositionName(String positionName) {
        return Arrays.stream(values())
                .filter(position -> position.positionName.equals(positionName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("이동 위치는 %s, %s 중에 입력해야합니다.", UP.positionName, DOWN.positionName)));
    }

    public String getPositionName() {
        return positionName;
    }
}
