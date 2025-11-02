package lotto.controller;

import lotto.domain.Lotto;
import lotto.validator.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;
import static lotto.domain.Lotto.UNIT;
import static lotto.domain.Lotto.MIN_NUMBER;
import static lotto.domain.Lotto.MAX_NUMBER;
import static lotto.domain.Lotto.LOTTO_NUMBER_COUNT;

public class LottoController {

    public void run() {
        Long purchaseCost = InputView.readPurchaseCost();
        Set<Integer> winningLottoNumbers = InputView.readWinningLottoNumbers();
        Integer bonusLottoNumbers = InputView.readBonusLottoNumbers();
        Validator.validateDuplicateBonusNumber(winningLottoNumbers, bonusLottoNumbers);

        List<Lotto> lottos = generateLottos(purchaseCost);

    }

    public List<Lotto> generateLottos(Long purchaseCost) {
        List<Lotto> lottos = new ArrayList<>();

        long lottoCount = purchaseCost / UNIT;

        for (int i = 0; i < lottoCount; i++) {
            List<Integer> randomNewLottoNumbers = pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, LOTTO_NUMBER_COUNT);
            randomNewLottoNumbers.sort(Integer::compareTo);
            Lotto lotto = new Lotto(randomNewLottoNumbers);
            lottos.add(lotto);
        }

        OutputView.printPurchasedLottos(lottoCount, lottos);
        return lottos;
    }
}
