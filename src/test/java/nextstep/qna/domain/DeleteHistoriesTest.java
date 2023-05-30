package nextstep.qna.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUserTest;

public class DeleteHistoriesTest {
	private DeleteHistories deleteHistories;

	@BeforeEach
	void setUp() {
		deleteHistories = new DeleteHistories();
	}

	@Test
	void 생성() {
		assertThat(deleteHistories).isInstanceOf(DeleteHistories.class);
	}

	@Test
	void 이력_저장() {
		DeleteHistories deleteHistories = new DeleteHistories();

		deleteHistories.add(new DeleteHistory(ContentType.QUESTION, 1L, NsUserTest.JAVAJIGI, LocalDateTime.now()));

		assertThat(deleteHistories.size()).isEqualTo(1);
	}
}
