package bridge.domain;

public class GameCount {
    private int gameCount;

    public GameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    public static GameCount init() {
        return new GameCount(1);
    }

    public void increase() {
        gameCount++;
    }

    public int getGameCount() {
        return gameCount;
    }
}
