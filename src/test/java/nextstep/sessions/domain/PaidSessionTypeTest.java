package nextstep.sessions.domain;

import nextstep.payments.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PaidSessionTypeTest {

    @DisplayName("isFull은 최대 수용인원 이상인지를 검사한다.")
    @ParameterizedTest(name = "{0} <= {1} : {2}")
    @CsvSource(value = {"3,2,false", "3,3,true", "3,4,true"})
    void isFull(int capacity, int userCount, boolean expectResult) {
        final Money price = new Money(800_000L);

        assertThat(new PaidSessionType(capacity, price).isFull(userCount))
                .isEqualTo(expectResult);
    }
}
