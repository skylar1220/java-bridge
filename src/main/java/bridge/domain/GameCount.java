package bridge.domain;

import java.util.Objects;

public class GameCount {
    public static final int DEFAULT_GAME_COUNT = 1;
    private int gameCount;

    private GameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    public static GameCount init() {
        return new GameCount(DEFAULT_GAME_COUNT);
    }

    public static GameCount from(int gameCount) {
        return new GameCount(gameCount);
    }

    public void increase() {
        gameCount++;
    }

    public int getGameCount() {
        return gameCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameCount gameCount1 = (GameCount) o;
        return gameCount == gameCount1.gameCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameCount);
    }
}
