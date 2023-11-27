package bridge.view;

import bridge.domain.BridgeGame;
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

    public void printMap(BridgeGame bridgeGame) {
        String map = formatter.toMap(bridgeGame);
        printer.printLine(map);
    }

    public void printResult(BridgeGame bridgeGame) {
        printFinalMap(bridgeGame);
        printSuccessResult(bridgeGame);
        printTotalTryCount(bridgeGame);
    }

    private void printFinalMap(BridgeGame bridgeGame) {
        printer.printLine("최종 게임 결과");
        printMap(bridgeGame);
    }

    private void printSuccessResult(BridgeGame bridgeGame) {
        String successResult = formatter.toSuccessResult(bridgeGame);
        printer.printLine("게임 성공 여부: %s", successResult);
    }

    private void printTotalTryCount(BridgeGame referee) {
        int totalTryCount = formatter.toTotalTryCount(referee);
        printer.printLine("총 시도한 횟수: %d", totalTryCount);
    }

    public void printExceptionMessage(String message) {
        printer.printLine(ERROR_MESSAGE_FORMAT + message);
        printer.printEmptyLine();
    }
}
