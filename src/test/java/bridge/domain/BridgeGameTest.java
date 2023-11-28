package bridge.domain;

import static bridge.domain.Position.DOWN;
import static bridge.domain.Position.UP;
import static bridge.domain.PositionMatch.MATCH;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BridgeGameTest {


    @Test
    void move_notSuccess() {
        // given
        AnswerBridge answerBridge = AnswerBridge.from(Arrays.asList("U", "D", "U"));
        PlayerBridge playerBridge = PlayerBridge.from(
                List.of(MovingResult.of(UP, MATCH), MovingResult.of(DOWN, MATCH)));
        BridgeGame bridgeGame = BridgeGame.of(answerBridge, playerBridge);
        Position inputPosition = DOWN;

        // when
        bridgeGame.move(inputPosition);

        // then
        assertThat(bridgeGame.isEndGame()).isFalse();
    }

    @Test
    void move_Succes() {
        // given
        AnswerBridge answerBridge = AnswerBridge.from(Arrays.asList("U", "D", "U"));
        PlayerBridge playerBridge = PlayerBridge.from(
                List.of(MovingResult.of(UP, MATCH), MovingResult.of(DOWN, MATCH)));
        BridgeGame bridgeGame = BridgeGame.of(answerBridge, playerBridge);
        Position inputPosition = UP;

        // when
        bridgeGame.move(inputPosition);

        // then
        assertThat(bridgeGame.isEndGame()).isTrue();
    }

    @DisplayName("재시도가 적용되면 게임횟수는 증가하고 플레이어 다리는 초기화된다.")
    @Test
    void applyRetry() {
        // given
        PlayerBridge playerBridge = PlayerBridge.from(List.of(MovingResult.of(UP, MATCH)));
        GameCount gameCount = GameCount.from(1);
        BridgeGame bridgeGame = BridgeGame.of(playerBridge, gameCount);
        RetryOption retryOption = RetryOption.RETRY;

        // when
        bridgeGame.applyRetry(retryOption);

        // then
        assertThat(gameCount).isEqualTo(GameCount.from(2));
        assertThat(bridgeGame.getPlayerBridge().getBridge()).isEmpty();
    }

    @DisplayName("종료가 적용되면 게임 종료여부는 참이 된다.")
    @Test
    void applyRetry_quit() {
        // given
        PlayerBridge playerBridge = PlayerBridge.from(List.of(MovingResult.of(UP, MATCH)));
        GameCount gameCount = GameCount.from(1);
        BridgeGame bridgeGame = BridgeGame.of(playerBridge, gameCount);
        RetryOption retryOption = RetryOption.QUIT;

        // when
        bridgeGame.applyRetry(retryOption);

        // then
        assertThat(bridgeGame.isEndGame()).isTrue();
    }

    @DisplayName("다리 끝까지 가지 않았고, 정답과 모두 일치하면 라운드를 계속 진행할 수 있다.")
    @Test
    void canPlayRound() {
        // given
        AnswerBridge answerBridge = AnswerBridge.from(Arrays.asList("U", "D", "U"));
        PlayerBridge playerBridge = PlayerBridge.from(
                List.of(MovingResult.of(UP, MATCH), MovingResult.of(DOWN, MATCH)));
        // when
        BridgeGame bridgeGame = BridgeGame.of(answerBridge, playerBridge);
        // then
        assertThat(bridgeGame.canPlayRound()).isTrue();
    }

    @DisplayName("다리 끝까지 가면 라운드를 계속 진행할 수 없다.")
    @Test
    void canPlayRound_end() {
        // given
        AnswerBridge answerBridge = AnswerBridge.from(Arrays.asList("U", "D", "U"));
        PlayerBridge playerBridge = PlayerBridge.from(
                List.of(MovingResult.of(UP, MATCH), MovingResult.of(DOWN, MATCH), MovingResult.of(UP, MATCH)));
        // when
        BridgeGame bridgeGame = BridgeGame.of(answerBridge, playerBridge);

        // then
        assertThat(bridgeGame.canPlayRound()).isFalse();
    }

    @DisplayName("정답과 모두 일치하지 않으면 라운드를 계속 진행할 수 없다.")
    @Test
    void canPlayRound_notMatch() {
        // given
        AnswerBridge answerBridge = AnswerBridge.from(Arrays.asList("U", "D", "U"));
        PlayerBridge playerBridge = PlayerBridge.from(
                List.of(MovingResult.of(DOWN, MATCH), MovingResult.of(UP, MATCH), MovingResult.of(DOWN, MATCH)));
        // when
        BridgeGame bridgeGame = BridgeGame.of(answerBridge, playerBridge);

        // then
        assertThat(bridgeGame.canPlayRound()).isFalse();
    }
}
