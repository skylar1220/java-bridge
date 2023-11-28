package bridge.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerBridge {
    private final List<Position> bridge;

    private AnswerBridge(List<Position> positions) {
        this.bridge = positions;
    }

    public static AnswerBridge from(List<String> positions) {
        return new AnswerBridge(convertToBridge(positions));
    }

    public static AnswerBridge fromEmpty() {
        return new AnswerBridge(new ArrayList<>());
    }

    private static List<Position> convertToBridge(List<String> positions) {
        return positions.stream()
                .map(Position::fromUserInput)
                .collect(Collectors.toList());
    }

    public PositionMatch match(int positionIndex, Position inpuPosition) {
        Position answerPosition = bridge.get(positionIndex);
        boolean positionMatch = inpuPosition.equals(answerPosition);
        return PositionMatch.from(positionMatch);
    }

    public boolean isSameSize(int size) {
        return bridge.size() == size;
    }

    public List<Position> getBridge() {
        return bridge;
    }
}
