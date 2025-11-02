package lotto.validator;

import lotto.domain.Lotto;

import java.util.Arrays;
import java.util.Set;

public class Validator {

    private static final String INVALID_PURCHASE_COST_UNIT_ERROR_MESSAGE = "비용은 1,000원 단위로 입력해야 합니다.";
    private static final String INVALID_PURCHASE_COST_FORMAT_ERROR_MESSAGE = "구입 비용은 숫자만 입력 가능합니다.";
    private static final String INVALID_WINNING_LOTTO_NUMBER_FORMAT_ERROR_MESSAGE = "당첨 번호를 잘못 입력하셨습니다.";
    private static final String INVALID_LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "로또 번호는 1에서 45 사이의 숫자여야 합니다.";
    private static final String INVALID_DUPLICATE_WINNING_LOTTO_NUMBER = "첨 번호는 중복될 수 없습니다.";
    private static final String INVALID_LOTTO_NUMBER_COUNT_ERROR_MESSAGE = "로또 번호는 6개여야 합니다.";

    public static void validateInputPurchaseCost(String cost) {

        Long purchaseCost = parseInput(cost, INVALID_PURCHASE_COST_FORMAT_ERROR_MESSAGE);

        if (purchaseCost % Lotto.UNIT != 0) {
            throw new IllegalArgumentException(INVALID_PURCHASE_COST_UNIT_ERROR_MESSAGE);
        }
    }

    public static void validateInputWinningLottoNumbers(String[] winningLottoNumbers) {
        Arrays.stream(winningLottoNumbers)
                .forEach(Validator::validateInputLottoNumber);

        if (winningLottoNumbers.length != Lotto.UNIT) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_COUNT_ERROR_MESSAGE);
        }
    }

    public static void validateInputLottoNumber(String inputBonusLottoNumber) {
        Long lottoNumber = parseInput(inputBonusLottoNumber, INVALID_WINNING_LOTTO_NUMBER_FORMAT_ERROR_MESSAGE);

        if (lottoNumber < Lotto.MIN_NUMBER || lottoNumber > Lotto.MAX_NUMBER) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    public static void validateDuplicateLottoNumbers(Set<Integer> lottoNumbers) {
        if (lottoNumbers.size() != Lotto.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(INVALID_DUPLICATE_WINNING_LOTTO_NUMBER);
        }
    }

    private static Long parseInput(String input, String errorMessage) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
