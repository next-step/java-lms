package nextstep.courses.domain.enums;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StatusTest {

	@DisplayName("모집중 상태인지 확인한다.")
	@ParameterizedTest
	@MethodSource("statusAndBoolean")
	void validate_is_applying(Status status, boolean expected) {
		assertThat(status.isApplying()).isEqualTo(expected);
	}

	static Stream<Arguments> statusAndBoolean() {
		return Stream.of(
			Arguments.arguments(Status.READY, false),
			Arguments.arguments(Status.APPLYING, true),
			Arguments.arguments(Status.END, false)

		);
	}
}
