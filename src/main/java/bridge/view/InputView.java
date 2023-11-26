package bridge.view;

import bridge.domain.BridegeSize;
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

    public String readMoving() {
        return null;
    }

    public String readGameCommand() {
        return null;
    }
}
