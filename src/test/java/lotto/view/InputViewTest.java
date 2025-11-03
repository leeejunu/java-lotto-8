package lotto.view;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

class InputViewTest {
    @Test
    @DisplayName("정상적인 당첨 번호 입력은 파싱되어 Set으로 반환된다.")
    void 정상적인_당첨번호_입력() {
        String input = "1,2,3,4,5,6";

        Set<Integer> result = InputView.parseWinningLottoNumbers(input);

        Assertions.assertThat(result).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("45를 초과한 당첨 번호 입력 시 예외 발생")
    void 당첨번호_45초과_예외() {
        String input = "1,2,3,4,5,46";

        Assertions.assertThatThrownBy(() -> InputView.parseWinningLottoNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 당첨 번호 입력 시 예외 발생")
    void 당첨번호_중복_예외() {
        String input = "1,2,3,4,5,5";

        Assertions.assertThatThrownBy(() -> InputView.parseWinningLottoNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("숫자가 아닌 값 입력 시 예외 발생")
    void 당첨번호_문자_예외() {
        String input = "1,2,3,4,5,a";


        Assertions.assertThatThrownBy(() -> InputView.parseWinningLottoNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}