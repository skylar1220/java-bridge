package bridge.domain;

public class BridegeSize {
    public static final int MIN_BRIDGE_SIZE = 3;
    public static final int MAX_BRIDGE_SIZE = 20;
    private final int bridgeSize;

    public BridegeSize(int bridgeSize) {
        validateRange(bridgeSize);
        this.bridgeSize = bridgeSize;
    }

    private void validateRange(int bridgeSize) {
        if (!isInRange(bridgeSize)) {
            throw new IllegalArgumentException(
                    String.format("다리 길이는 %d이상 %d이하의 숫자로 입력해주세요", MIN_BRIDGE_SIZE, MAX_BRIDGE_SIZE));
        }
    }

    private boolean isInRange(int bridgeSize) {
        return bridgeSize >= MIN_BRIDGE_SIZE && bridgeSize <= MAX_BRIDGE_SIZE;
    }

    public int getBridgeSize() {
        return bridgeSize;
    }
}
