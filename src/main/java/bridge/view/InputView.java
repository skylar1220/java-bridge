package bridge.view;

import bridge.domain.RetryOption;
import bridge.domain.BridegeSize;
import bridge.domain.Position;
import bridge.util.converter.Converter;
import bridge.view.printer.Printer;
import bridge.view.reader.Reader;
import bridge.view.validator.InputValidator;

public class InputView {
    private final Reader reader;
    private final Printer printer;
    private final InputValidator validator;

    private InputView(Reader reader, Printer printer, InputValidator validator) {
        this.reader = reader;
        this.printer = printer;
        this.validator = validator;
    }

    public static InputView of(Reader reader, Printer printer) {
        return new InputView(reader, printer, InputValidator.getInstance());
    }

    public BridegeSize readBridgeSize() {
        printer.printLine("다리의 길이를 입력해주세요.");
        String bridegeSize = reader.readLine();
        validator.validateBridegeSize(bridegeSize, "다리 길이");
        return new BridegeSize(Converter.convertToInt(bridegeSize));
    }

    public Position readMoving() {
        printer.printLine("이동할 칸을 선택해주세요. (위: U, 아래: D)");
        String moving = reader.readLine();
        validator.validateMoving(moving, "이동할 칸");
        return Position.fromUserInput(moving);
    }

    public RetryOption readGameCommand() {
        printer.printLine("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
        String retry = reader.readLine();
        validator.validateRetryOption(retry, "재시도 여부");
        return RetryOption.from(retry);
    }
}
