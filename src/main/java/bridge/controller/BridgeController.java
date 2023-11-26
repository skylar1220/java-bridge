package bridge.controller;

import bridge.domain.AnswerBridge;
import bridge.domain.BridegeSize;
import bridge.domain.BridgeGame;
import bridge.domain.BridgeMaker;
import bridge.domain.BridgeNumberGenerator;
import bridge.domain.Position;
import bridge.domain.Referee;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.function.Supplier;

public class BridgeController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeController(InputView inputView, OutputView outputView, BridgeNumberGenerator bridgeNumberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public void run() {
        showStart();
        BridegeSize bridgeSize = readWithRetry(inputView::readBridgeSize);
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        AnswerBridge answerBridge = AnswerBridge.from(bridgeMaker.makeBridge(bridgeSize.getBridgeSize()));
        BridgeGame bridgeGame = BridgeGame.initGame(answerBridge);
        Referee referee = Referee.init(bridgeGame);
        while (referee.canPlay()) {
            playRound(bridgeGame);
            RetryOption retryOption = readWithRetry(inputView::readGameCommand);
            referee.applyRetry(retryOption);
        }
        outputView.printResult(bridgeGame, referee);
    }

    private void showStart() {
        outputView.printStart();
    }

    private void playRound(BridgeGame bridgeGame) {
        while (bridgeGame.hasRightMovement() && bridgeGame.canPlayGame()) {
            Position inpuPosition = readWithRetry(inputView::readMoving);
            bridgeGame.move(inpuPosition);
            outputView.printMap(bridgeGame);
        }
    }

    private <T> T readWithRetry(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return readWithRetry(supplier);
        }
    }
}
