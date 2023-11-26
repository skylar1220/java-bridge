package bridge.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Bridge {
    private final List<Position> positions;

    public Bridge(List<Position> positions) {
        this.positions = positions;
    }

    public static Bridge from(List<String> positions) {
       return new Bridge(convertToBridge(positions));
    }

    private static List<Position> convertToBridge(List<String> positions) {
        return positions.stream()
                .map(Position::fromPositionName)
                .collect(Collectors.toList());
    }
}
