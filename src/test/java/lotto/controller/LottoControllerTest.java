package lotto.controller;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoControllerTest {

    @Test
    @DisplayName("구입 금액에 따라 올바른 개수의 로또가 생성된다.")
    void 로또_구입금액에_따라_올바른_개수의_로또가_생성된다() {
        LottoController controller = new LottoController();
        Long purchaseCost = 8000L;

        List<Lotto> lottos = controller.generateLottos(purchaseCost);

        assertThat(lottos).hasSize(8);
    }

    @Test
    @DisplayName("생성된 로또의 번호는 1~45 범위 내에 있어야 한다.")
    void 생성된_로또의_번호는_1에서_45_사이이다() {
        LottoController controller = new LottoController();
        Long purchaseCost = 1000L;

        List<Lotto> lottos = controller.generateLottos(purchaseCost);
        List<Integer> numbers = lottos.get(0).getNumbers();

        assertThat(numbers).allMatch(n -> n >= 1 && n <= 45);
    }

    @Test
    @DisplayName("로또 1장은 중복되지 않은 6개의 번호로 구성된다.")
    void 로또_번호는_중복되지_않고_6개이다() {
        LottoController controller = new LottoController();
        Long purchaseCost = 1000L;

        List<Lotto> lottos = controller.generateLottos(purchaseCost);
        List<Integer> numbers = lottos.get(0).getNumbers();

        assertThat(numbers).doesNotHaveDuplicates();
        assertThat(numbers).hasSize(6);
    }
}
