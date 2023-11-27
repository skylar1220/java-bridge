package bridge.view.formatter;

import bridge.domain.BridgeGame;
import bridge.domain.GameSuccess;
import bridge.domain.MovingResult;
import bridge.domain.PlayerBridge;
import bridge.domain.Position;
import java.util.ArrayList;
import java.util.List;

public class OutputFomatter {
    public static final String BLANK = " ";

    public String toMap(BridgeGame bridgeGame) {
        PlayerBridge playerBridge = bridgeGame.getPlayerBridge();
        List<MovingResult> movingResults = playerBridge.getBridge();

        List<String> upline = new ArrayList<>();
        List<String> downline = new ArrayList<>();

        addMarkToLine(movingResults, upline, downline);
        return convertToResultMap(upline, downline);
    }

    private void addMarkToLine(List<MovingResult> movingResults, List<String> upline, List<String> downline) {
        for (MovingResult movingResult : movingResults) {
            Position position = movingResult.getPosition();
            String positionMatchMark = movingResult.getPositionMatch().getMark();

            addToLine(position, positionMatchMark, upline, downline);
        }
    }

    private void addToLine(Position position, String positionMatchMark, List<String> upline, List<String> downline) {
        if (position.equals(Position.UP)) {
            upline.add(positionMatchMark);
            downline.add(BLANK);
        } else if (position.equals(Position.DOWN)) {
            upline.add(BLANK);
            downline.add(positionMatchMark);
        }
    }

    private String convertToResultMap(List<String> upline, List<String> downline) {
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
