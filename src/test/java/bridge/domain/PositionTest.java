package bridge.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {
    @DisplayName("정상적이지 않은 에 대해서 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "u2", "u"})
    void valid_position(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> Position.fromUserInput(input));
    }

    @DisplayName("정상적인 에 대해서 예외를 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"U", "D"})
    void invalid_position(String input) {
        assertThatCode(() -> Position.fromUserInput(input)).doesNotThrowAnyException();
    }
}
