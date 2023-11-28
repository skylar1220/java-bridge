package bridge.controller;

import bridge.domain.AnswerBridge;
import bridge.domain.BridegeSize;
import bridge.domain.BridgeGame;
import bridge.domain.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.domain.Position;
import bridge.domain.RetryOption;
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
        BridegeSize bridgeSize = getBridgeSize();
        AnswerBridge answerBridge = generateAnswerBridge(bridgeSize);
        BridgeGame bridgeGame = BridgeGame.initGame(answerBridge);
        playGame(bridgeGame);
        outputView.printResult(bridgeGame);
    }

    private void showStart() {
        outputView.printStart();
    }

    private BridegeSize getBridgeSize() {
        return readWithRetry(inputView::readBridgeSize);
    }

    private AnswerBridge generateAnswerBridge(BridegeSize bridgeSize) {
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        return AnswerBridge.from(bridgeMaker.makeBridge(bridgeSize.getBridgeSize()));
    }

    private void playGame(BridgeGame bridgeGame) {
        while (bridgeGame.canPlayGame()) {
            playRound(bridgeGame);
            applyRetry(bridgeGame);
        }
    }

    private void playRound(BridgeGame bridgeGame) {
        while (bridgeGame.canPlayRound()) {
            Position inpuPosition = readWithRetry(inputView::readMoving);
            bridgeGame.move(inpuPosition);
            outputView.printMap(bridgeGame);
        }
    }

    private void applyRetry(BridgeGame bridgeGame) {
        if (!bridgeGame.isGameSuccess()) {
            RetryOption retryOption = readWithRetry(inputView::readGameCommand);
            bridgeGame.applyRetry(retryOption);
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
