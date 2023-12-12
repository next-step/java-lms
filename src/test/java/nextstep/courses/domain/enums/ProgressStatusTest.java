package nextstep.courses.domain.enums;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ProgressStatusTest {

	@DisplayName("종료된 강의는 신청할 수 없다.")
	@ParameterizedTest
	@MethodSource("progressStatusAndBoolean")
	void validate_is_finish(ProgressStatus progressStatus, boolean expected) {
		assertThat(progressStatus.isFinish()).isEqualTo(expected);
	}

	static Stream<Arguments> progressStatusAndBoolean() {
		return Stream.of(
			Arguments.arguments(ProgressStatus.READY, false),
			Arguments.arguments(ProgressStatus.IN_PROGRESS, false),
			Arguments.arguments(ProgressStatus.FINISH, true)
		);
	}
}
