package bridge.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BridgeSizeTest {
    @DisplayName("정상적이지 않은 다리 길이에 대해서 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 2, 21})
    void invalid_bridegeSize(int input) {
        assertThatIllegalArgumentException().isThrownBy(() -> new BridegeSize(input));
    }

    @DisplayName("정상적인 다리 길이에 대해서 예외를 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 19, 20})
    void valid_bridegeSize(int input) {
        assertThatCode(() -> new BridegeSize(input)).doesNotThrowAnyException();
    }
}
