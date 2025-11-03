package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.validator.Validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


public class InputView {

    private static final String INPUT_PURCHASE_COST_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_NUMBERS = "\n당첨 번호를 입력해주세요.";
    private static final String INPUT_BONUS_LOTTO_NUMBERS = "\n보너스 번호를 입력해 주세요.";

    public static Long readPurchaseCost() {
        System.out.println(INPUT_PURCHASE_COST_MESSAGE);
        String purchaseCost = Console.readLine();
        Validator.validateInputPurchaseCost(purchaseCost);
        return Long.parseLong(purchaseCost);
    }

    public static Set<Integer> readWinningLottoNumbers() {
        System.out.println(INPUT_WINNING_LOTTO_NUMBERS);
        String input = Console.readLine();
        return parseWinningLottoNumbers(input);
    }

    public static Set<Integer> parseWinningLottoNumbers(String input) {
        String[] winningLottoNumbers = input.split(",", -1);

        Validator.validateInputWinningLottoNumbers(winningLottoNumbers);

        Set<Integer> duplicateLottoNumbers = Arrays.stream(winningLottoNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        Validator.validateDuplicateWinningLottoNumbers(duplicateLottoNumbers);
        return duplicateLottoNumbers;
    }

    public static Integer readBonusLottoNumbers() {
        System.out.println(INPUT_BONUS_LOTTO_NUMBERS);
        String bonusLottoNumber = Console.readLine();
        Validator.validateInputLottoNumber(bonusLottoNumber);
        return Integer.parseInt(bonusLottoNumber);
    }
}
