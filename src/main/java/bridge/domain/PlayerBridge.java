package bridge.domain;

import java.util.ArrayList;
import java.util.List;

public class PlayerBridge {
    private final List<MovingResult> bridge;

    private PlayerBridge(List<MovingResult> bridge) {
        this.bridge = bridge;
    }

    public static PlayerBridge init() {
        return new PlayerBridge(new ArrayList<>());
    }

    public boolean isStart() {
        return bridge.isEmpty();
    }

    public void applyMovement(AnswerBridge answerBridge, Position inpuPosition) {
        int positionIndex = bridge.size();
        PositionMatch positionMatch = answerBridge.match(positionIndex, inpuPosition);
        bridge.add(MovingResult.of(inpuPosition, positionMatch));
    }

    public boolean hasFailMovement(AnswerBridge answerBridge) {
        return bridge.stream()
                .anyMatch(MovingResult::isFailMovement);
    }

    public boolean isSameSize(AnswerBridge answerBridge) {
        return answerBridge.isSameSize(bridge.size());
    }

    public List<MovingResult> getBridge() {
        return bridge;
    }
}
