package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.validator.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.EnumMap;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;
import static lotto.domain.Lotto.UNIT;
import static lotto.domain.Lotto.MIN_NUMBER;
import static lotto.domain.Lotto.MAX_NUMBER;
import static lotto.domain.Lotto.LOTTO_NUMBER_COUNT;

public class LottoController {

    Map<Rank, Integer> result = new EnumMap<>(Rank.class);

    public void run() {
        Long purchaseCost = InputView.readPurchaseCost();
        Set<Integer> winningLottoNumbers = InputView.readWinningLottoNumbers();
        Integer bonusLottoNumber = InputView.readBonusLottoNumbers();
        Validator.validateDuplicateBonusNumber(winningLottoNumbers, bonusLottoNumber);

        List<Lotto> lottos = generateLottos(purchaseCost);

        lottos.forEach((lotto) -> {
            int matchCount = lotto.countMatchedNumbers(winningLottoNumbers);
            boolean bonusMatched = lotto.getNumbers().contains(bonusLottoNumber);

            Rank rank = Rank.valueOf(matchCount, bonusMatched);

            result.put(rank, result.getOrDefault(rank, 0) + 1);
        });

        OutputView.printResult(result);
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
