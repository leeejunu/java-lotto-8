package lotto.view;

import lotto.domain.Lotto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PURCHASED_LOTTO_MESSAGE = "개를 구매했습니다.";

    public static void printPurchasedLottos(Long purchaseLottoCount, List<Lotto> lottos) {
        System.out.println();
        System.out.println(purchaseLottoCount + PURCHASED_LOTTO_MESSAGE);
        lottos.forEach(lotto -> System.out.println(formatLottoNumbers(lotto.getNumbers())));
    }

    private static String formatLottoNumbers(List<Integer> lottoNumbers) {
        return "[" + lottoNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "))
                + "]";
    }
}

