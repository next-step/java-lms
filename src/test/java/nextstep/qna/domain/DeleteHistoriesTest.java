package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUserTest;

public class DeleteHistoriesTest {

	@Test
	@DisplayName("답변 목록을 모두 삭제 상태로 바꾼 후 삭제 히스토리 리스트 리턴")
	void delete_성공() {
		Answer answer = new Answer(new TextBody(NsUserTest.JAVAJIGI, "Answers Contents1", false), QuestionTest.Q1);
		Answers answers = Answers.of(answer);

		DeleteHistories deleteHistories = new DeleteHistories();

		assertThat(deleteHistories.addAll(answers))
			.containsOnly(new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now()));
	}
}
