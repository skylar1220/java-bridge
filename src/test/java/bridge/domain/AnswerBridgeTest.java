package bridge.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class AnswerBridgeTest {
    AnswerBridge answerBridge = AnswerBridge.from(List.of("U", "D", "U"));

    @Test
    void from() {
        assertThat(answerBridge.getBridge())
                .containsExactly(Position.UP, Position.DOWN, Position.UP);
    }

    @Test
    void match() {
        PositionMatch positionMatch = answerBridge.match(2, Position.UP);
        PositionMatch positionNotMatch = answerBridge.match(2, Position.DOWN);

        assertThat(positionMatch).isEqualTo(PositionMatch.MATCH);
        assertThat(positionNotMatch).isEqualTo(PositionMatch.NOT_MATCHED);
    }
}
