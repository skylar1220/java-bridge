package bridge.domain;

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
}
