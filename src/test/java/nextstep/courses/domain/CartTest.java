package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static nextstep.courses.domain.SessionTest.S1;
import static nextstep.courses.domain.SessionTest.S2;
import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {

    @Test
    void create() {
        Cart cart = new Cart(Stream.of(S1, S2).collect(Collectors.toSet()));
        assertThat(cart).isNotNull();
    }
}
