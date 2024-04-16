package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FreeOrPaidTest {

    @Test
    void is_free() {
        assertThat(FreeOrPaid.free().isFree()).isTrue();
    }
    
    @Test
    void need_to_pay() {
        assertThat(new FreeOrPaid(false, 20000).canPay(new Money(20000))).isTrue();
    }
}