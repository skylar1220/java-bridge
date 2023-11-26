package bridge.domain;

import bridge.controller.RetryOption;

public class Referee {
    private final BridgeGame bridgeGame;
    private final GameCount gameCount;
    private boolean canPlay;

    public Referee(BridgeGame bridgeGame, GameCount gameCount, boolean canPlay) {
        this.bridgeGame = bridgeGame;
        this.gameCount = gameCount;
        this.canPlay = canPlay;
    }

    public static Referee init(BridgeGame bridgeGame) {
        boolean canPlay = true;
        return new Referee(bridgeGame, GameCount.init(), canPlay);
    }

    public boolean canPlay() {
        return canPlay && bridgeGame.canPlayGame();
    }

    public void applyRetry(RetryOption retryOption) {
        if (retryOption.equals(RetryOption.RETRY)) {
            gameCount.increase();
            bridgeGame.retry();
        }
        if (retryOption.equals(RetryOption.QUIT)) {
            canPlay = false;
        }
    }

    public int getGameCount() {
        return gameCount.getGameCount();
    }
}
