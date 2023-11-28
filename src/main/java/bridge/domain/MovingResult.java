package bridge.domain;

import java.util.Objects;

public class MovingResult {
    private final Position position;
    private final PositionMatch positionMatch;

    public MovingResult(Position position, PositionMatch positionMatch) {
        this.position = position;
        this.positionMatch = positionMatch;
    }

    public static MovingResult of(Position position, PositionMatch positionMatch) {
        return new MovingResult(position, positionMatch);
    }

    public boolean isFailMovement() {
        return positionMatch.isNotMatch();
    }

    public Position getPosition() {
        return position;
    }

    public PositionMatch getPositionMatch() {
        return positionMatch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovingResult that = (MovingResult) o;
        return position == that.position && positionMatch == that.positionMatch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, positionMatch);
    }
}
