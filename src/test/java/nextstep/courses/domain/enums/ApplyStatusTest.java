package nextstep.courses.domain.enums;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ApplyStatusTest {
	@DisplayName("모집중인 강의만 신청할 수 있다.")
	@ParameterizedTest
	@MethodSource("applyStatusAndBoolean")
	void validate_is_applying(ApplyStatus applyStatus, boolean expected) {
		assertThat(applyStatus.isApplying()).isEqualTo(expected);
	}

	static Stream<Arguments> applyStatusAndBoolean() {
		return Stream.of(
			Arguments.arguments(ApplyStatus.APPLYING, true),
			Arguments.arguments(ApplyStatus.CLOSED, false)
		);
	}
}
