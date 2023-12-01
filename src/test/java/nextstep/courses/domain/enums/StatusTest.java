package nextstep.courses.domain.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StatusTest {

	@DisplayName("모집중 상태인지 확인한다.")
	@Test
	void valid_is_applying() {
		Assertions.assertThat(Status.APPLYING.isApplying()).isEqualTo(true);
	}
}
