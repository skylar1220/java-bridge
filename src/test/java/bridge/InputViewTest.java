package bridge;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bridge.view.InputView;
import bridge.view.printer.Printer;
import bridge.view.reader.Reader;
import mock.FakePrinter;
import mock.FakeReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputViewTest {
    private Printer printer = new FakePrinter();

    @DisplayName("정상적이지 않은 다리 길이에 대해서 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "2two", "2147483648", "-2147483649", "-1", "0", "2", "21"})
    void readBridgeSize(String input) {
        Reader reader = new FakeReader(input);
        InputView inputView = InputView.of(reader, printer);

        assertThatIllegalArgumentException().isThrownBy(inputView::readBridgeSize);
    }
}
