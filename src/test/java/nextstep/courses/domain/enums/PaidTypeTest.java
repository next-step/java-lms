package nextstep.courses.domain.enums;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PaidTypeTest {

	@DisplayName("강의가 무료인지 확인한다.")
	@ParameterizedTest
	@MethodSource("paidTypeAndBoolean")
	void valid_is_free(PaidType type, boolean expected) {
		assertThat(type.isFree()).isEqualTo(expected);
	}

	static Stream<Arguments> paidTypeAndBoolean() {
		return Stream.of(
			Arguments.arguments(PaidType.PAID, false),
			Arguments.arguments(PaidType.FREE, true)
		);
	}
}
