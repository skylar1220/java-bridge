package bridge.view.formatter;

import bridge.domain.BridgeGame;
import bridge.domain.GameSuccess;
import bridge.domain.MovingResult;
import bridge.domain.PlayerBridge;
import bridge.domain.Position;
import java.util.List;
import java.util.stream.Collectors;

public class OutputFomatter {
    public static final String BLANK = " ";

    public String toMap(BridgeGame bridgeGame) {
        PlayerBridge playerBridge = bridgeGame.getPlayerBridge();
        List<MovingResult> movingResults = playerBridge.getBridge();

        List<String> upline = getPositionMatches(movingResults, Position.UP);
        List<String> downline = getPositionMatches(movingResults, Position.DOWN);

        return convertResultMap(upline, downline);
    }

    private List<String> getPositionMatches(List<MovingResult> movingResults, Position position) {
        return movingResults.stream()
                .filter(movingResult -> movingResult.getPosition() == position)
                .map(movingResult -> movingResult.getPositionMatch().getMark())
                .collect(Collectors.toList());
    }

    private String convertResultMap(List<String> upline, List<String> downline) {
        String uplineMark = String.join(" | ", upline);
        String downlineMark = String.join(" | ", downline);

        return String.format("[ %s ]", uplineMark)
                + System.lineSeparator()
                + String.format("[ %s ]", downlineMark);
    }

    public String toSuccessResult(BridgeGame bridgeGame) {
        GameSuccess successResult = bridgeGame.getSuccessResult();
        return successResult.getMessage();
    }

    public int toTotalTryCount(BridgeGame bridgeGame) {
        return bridgeGame.getGameCount();
    }
}
