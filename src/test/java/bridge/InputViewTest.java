package bridge;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bridge.view.InputView;
import bridge.view.printer.Printer;
import bridge.view.reader.Reader;
import bridge.mock.FakePrinter;
import bridge.mock.FakeReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputViewTest {
    private Printer printer = new FakePrinter();

    @DisplayName("정상적이지 않은 다리 길이에 대해서 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "2two", "2147483648", "-2147483649"})
    void readBridgeSize(String input) {
        Reader reader = new FakeReader(input);
        InputView inputView = InputView.of(reader, printer);

        assertThatIllegalArgumentException().isThrownBy(inputView::readBridgeSize);
    }
    @DisplayName("정상적이지 않은 이동할 칸에 대해서 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void readMoving(String input) {
        Reader reader = new FakeReader(input);
        InputView inputView = InputView.of(reader, printer);

        assertThatIllegalArgumentException().isThrownBy(inputView::readMoving);
    }
    @DisplayName("정상적이지 않은 재시도 여부에 대해서 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void readGameCommand(String input) {
        Reader reader = new FakeReader(input);
        InputView inputView = InputView.of(reader, printer);

        assertThatIllegalArgumentException().isThrownBy(inputView::readGameCommand);
    }
}
