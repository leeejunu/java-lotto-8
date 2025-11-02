package lotto.controller;

import lotto.domain.Lotto;
import lotto.view.InputView;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;
import static lotto.domain.Lotto.UNIT;
import static lotto.domain.Lotto.MIN_NUMBER;
import static lotto.domain.Lotto.MAX_NUMBER;
import static lotto.domain.Lotto.LOTTO_NUMBER_COUNT;

public class LottoController {

    public void run() {
        Long purchaseCost = InputView.readPurchaseCost();
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

        return lottos;
    }
}
