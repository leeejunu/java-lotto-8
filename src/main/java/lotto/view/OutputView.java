package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PURCHASED_LOTTO_MESSAGE = "개를 구매했습니다.";

    private static final String STATISTICS_HEADER = "\n당첨 통계\n---";

    public static void printResult(Map<Rank, Integer> result) {
        System.out.println(STATISTICS_HEADER);
        System.out.printf("3개 일치 (5,000원) - %d개%n", result.getOrDefault(Rank.THREE, 0));
        System.out.printf("4개 일치 (50,000원) - %d개%n", result.getOrDefault(Rank.FOUR, 0));
        System.out.printf("5개 일치 (1,500,000원) - %d개%n", result.getOrDefault(Rank.FIVE, 0));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개%n", result.getOrDefault(Rank.FIVE_BONUS, 0));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개%n", result.getOrDefault(Rank.SIX, 0));

        double profit = calculateProfit(result);
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profit);
    }

    private static double calculateProfit(Map<Rank, Integer> result) {
        long totalPrize = 0;
        for (Rank rank : Rank.values()) {
            totalPrize += (long) rank.getPrize() * result.getOrDefault(rank, 0);
        }
        long totalSpent = result.values().stream().mapToInt(Integer::intValue).sum() * 1000L;
        return totalSpent == 0 ? 0 : (double) totalPrize / totalSpent * 100;
    }

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

