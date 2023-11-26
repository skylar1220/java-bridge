package bridge.view.formatter;

import bridge.domain.GameSuccess;
import bridge.domain.MovingResult;
import bridge.domain.PlayerBridge;
import bridge.domain.BridgeGame;
import bridge.domain.Position;
import bridge.domain.PositionMatch;
import bridge.domain.Referee;
import java.util.ArrayList;
import java.util.List;

public class OutputFomatter {
    public static final String BLANK = " ";

    public String toMap(BridgeGame bridgeGame) {
        PlayerBridge playerBridge = bridgeGame.getPlayerBridge();
        List<MovingResult> movingResults = playerBridge.getBridge();

        List<String> upline = new ArrayList<>();
        List<String> downline = new ArrayList<>();

        for (MovingResult movingResult : movingResults) {
            Position position = movingResult.getPosition();
            PositionMatch positionMatch = movingResult.getPositionMatch();

            String positionMatchMark = positionMatch.getMark();
            if (position.equals(Position.UP)) {
                upline.add(positionMatchMark);
                downline.add(BLANK);
            }
            if (position.equals(Position.DOWN)) {
                upline.add(BLANK);
                downline.add(positionMatchMark);
            }
        }
        return "[ " + String.join(" | ", upline) + " ]" + System.lineSeparator()
                + "[ " + String.join(" | ", downline) + " ]";
    }

    public String toSuccessResult(BridgeGame bridgeGame) {
        GameSuccess successResult = bridgeGame.getSuccessResult();
        return successResult.getMessage();
    }

    public int toTotalTryCount(Referee referee) {
        return referee.getGameCount();
    }
}
