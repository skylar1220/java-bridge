package bridge.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RetryOptionTest {
    @DisplayName("정상적이지 않은 재시도 입력에 대해서 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"r", "a", "r2"})
    void valid_position(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> RetryOption.from(input));
    }

    @DisplayName("정상적인 재시도 입력에 대해서 예외를 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"R", "Q"})
    void invalid_position(String input) {
        assertThatCode(() -> RetryOption.from(input)).doesNotThrowAnyException();
    }
}
