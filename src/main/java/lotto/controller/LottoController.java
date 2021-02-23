package lotto.controller;

import lotto.domain.LottoService;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.lottomachine.RandomLottoMachine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.Money;
import lotto.domain.Ticket;
import lotto.domain.rating.RatingInfo;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void start() {
        LottoService lottoService = new LottoService();
        LottoRepository lottoRepository = new LottoRepository();

        Ticket totalTicket = buyLotto();
        Ticket manualTicket = manualBuyTicket(totalTicket);

        generateLottoNumbers(manualTicket.getCount(), lottoRepository);
        printTicketCountAndLottoResult(totalTicket, lottoService, lottoRepository);

        WinningLotto winningLotto = buyWinningLotto();
        RatingInfo ratingInfo = lottoService.scratchLotto(lottoRepository, winningLotto);
        printWinningStats(ratingInfo, lottoService, totalTicket);
    }

    private Ticket buyLotto() {
        try {
            return tryBuyLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return buyLotto();
        }
    }

    private Ticket tryBuyLotto() {
        int money = InputView.getMoney();
        return new Ticket(new Money(money));
    }

    private Ticket manualBuyTicket(Ticket totalTicket) {
        try {
            return tryManualBuyTicket(totalTicket);
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return manualBuyTicket(totalTicket);
        }
    }

    private Ticket tryManualBuyTicket(Ticket totalTicket) {
        int count = InputView.getManualTicketCount();
        return new Ticket(count, totalTicket);
    }

    private void generateLottoNumbers(final int count, final LottoRepository lottoRepository) {
        for (int i = 0; i < count; i++) {
            Lotto lotto = manualBuyLotto();
            lottoRepository.generateLottoByManual(lotto);
        }
    }

    private Lotto manualBuyLotto() {
        try {
            return tryManualBuyLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return manualBuyLotto();
        }
    }

    private Lotto tryManualBuyLotto() {
        return Lotto.from(InputView.getLottoNumbers());
    }

    private WinningLotto buyWinningLotto() {
        try {
            return tryBuyWinningLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return buyWinningLotto();
        }
    }

    private WinningLotto tryBuyWinningLotto() {
        Lotto lotto = Lotto.from(InputView.getWinningNumbers());
        LottoNumber bonusNumber = new LottoNumber(InputView.getBonusBall());
        return new WinningLotto(lotto, bonusNumber);
    }

    private void printTicketCountAndLottoResult(Ticket ticket, LottoService lottoService,
        LottoRepository lottoRepository) {
        OutputView.printBuyLotto(ticket.getCount());
        OutputView.printLottoResults(
            lottoService.getLotto(lottoRepository, new RandomLottoMachine(), ticket));
    }

    private void printWinningStats(RatingInfo ratingInfo, LottoService lottoService,
        Ticket ticket) {
        OutputView
            .printWinningStats(ratingInfo,
                lottoService.calculateEarningRate(ratingInfo, ticket.getPrice()));
    }
}
