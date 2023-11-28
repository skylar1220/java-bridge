package bridge.domain;

import static bridge.domain.Position.DOWN;
import static bridge.domain.Position.UP;
import static bridge.domain.PositionMatch.MATCH;
import static bridge.domain.PositionMatch.NOT_MATCHED;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerBridgeTest {
    AnswerBridge answerBridge = AnswerBridge.from(List.of("U", "D", "U"));

    @DisplayName("일치하는 경우, 플레이어 다리에 이동방향과 일치여부가 추가된다.")
    @Test
    void applyMovement() {
        // given
        PlayerBridge playerBridge = PlayerBridge.from(
                List.of(MovingResult.of(UP, MATCH), MovingResult.of(DOWN, MATCH)));

        // when
        playerBridge.applyMovement(answerBridge, UP);

        // then
        assertThat(playerBridge.getBridge())
                .containsExactly(MovingResult.of(UP, MATCH), MovingResult.of(DOWN, MATCH), MovingResult.of(UP, MATCH));
    }

    @DisplayName("불일치하는 경우, 플레이어 다리에 이동방향과 불일치여부가 추가된다.")
    @Test
    void applyMovement_notMatch() {
        // given
        PlayerBridge playerBridge = PlayerBridge.from(
                List.of(MovingResult.of(UP, MATCH), MovingResult.of(DOWN, MATCH)));

        // when
        playerBridge.applyMovement(answerBridge, DOWN);

        // then
        assertThat(playerBridge.getBridge())
                .containsExactly(MovingResult.of(UP, MATCH), MovingResult.of(DOWN, MATCH), MovingResult.of(DOWN, NOT_MATCHED));
    }
}
