package bridge.domain;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final AnswerBridge answerBridge;
    private PlayerBridge playerBridge;
    private boolean endGame;
    private final GameCount gameCount;

    public BridgeGame(AnswerBridge answerBridge, PlayerBridge playerBridge, boolean endGame, GameCount gameCount) {
        this.answerBridge = answerBridge;
        this.playerBridge = playerBridge;
        this.endGame = endGame;
        this.gameCount = gameCount;
    }

    public static BridgeGame initGame(AnswerBridge answerBridge) { // 여기 분리
        PlayerBridge playerBridge = PlayerBridge.init();
        boolean isSuccess = false;
        GameCount gameCount = GameCount.init();
        return new BridgeGame(answerBridge, playerBridge, isSuccess, gameCount);
    }

    public static BridgeGame of(AnswerBridge answerBridge, PlayerBridge playerBridge) {
        boolean isSuccess = false;
        GameCount gameCount = GameCount.init();
        return new BridgeGame(answerBridge, playerBridge, isSuccess, gameCount);
    }

    public static BridgeGame of(PlayerBridge playerBridge, GameCount gameCount) {
        AnswerBridge answerBridge = AnswerBridge.fromEmpty();
        boolean isSuccess = false;
        return new BridgeGame(answerBridge, playerBridge, isSuccess, gameCount);
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(Position inpuPosition) {
        playerBridge.applyMovement(answerBridge, inpuPosition);
        applySuccessCecking();
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        playerBridge = PlayerBridge.init();
    }

    private void applySuccessCecking() {
        if (isGameSuccess()) {
            endGame = true;
        }
    }

    public void applyRetry(RetryOption retryOption) {
        if (retryOption.isRetry()) {
            gameCount.increase();
            retry();
        }
        if (retryOption.isQuit()) {
            endGame = true;
        }
    }

    public boolean canPlayGame() {
        return !endGame;
    }

    public boolean canPlayRound() {
        return !reachBridgeSize() && hasRightMovement();
    }

    public boolean isGameSuccess() {
        return reachBridgeSize() && hasRightMovement();
    }

    private boolean reachBridgeSize() {
        return playerBridge.isSameSize(answerBridge);
    }

    private boolean hasRightMovement() {
        if (playerBridge.isStart()) {
            return true;
        }
        return !playerBridge.hasFailMovement(answerBridge);
    }

    public GameSuccess getSuccessResult() {
        boolean finalSuccess = !playerBridge.hasFailMovement(answerBridge);
        return GameSuccess.from(finalSuccess);
    }

    public PlayerBridge getPlayerBridge() {
        return playerBridge;
    }

    public int getGameCount() {
        return gameCount.getGameCount();
    }

    public boolean isEndGame() {
        return endGame;
    }
}
