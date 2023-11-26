package bridge.view;

import bridge.view.formatter.OutputFomatter;
import bridge.view.printer.Printer;

public class OutputView {
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] ";
    private final Printer printer;
    private final OutputFomatter formatter;

    public OutputView(Printer printer, OutputFomatter formatter) {
        this.printer = printer;
        this.formatter = formatter;
    }

    public void printStart() {
        printer.printLine("다리 건너기 게임을 시작합니다.");
    }

    public void printMap() {
//        int template = formatter.toTemplate(rawTemplate);
//        printer.printLine("%d개", template);
    }

    public void printResult() {
    }

    public void printExceptionMessage(String message) {
        printer.printLine(ERROR_MESSAGE_FORMAT + message);
        printer.printEmptyLine();
    }
}
