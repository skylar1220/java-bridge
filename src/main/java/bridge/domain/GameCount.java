package bridge.domain;

public class GameCount {
    public static final int DEFAULT_GAME_COUNT = 1;
    private int gameCount;

    public GameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    public static GameCount init() {
        return new GameCount(DEFAULT_GAME_COUNT);
    }

    public void increase() {
        gameCount++;
    }

    public int getGameCount() {
        return gameCount;
    }
}
