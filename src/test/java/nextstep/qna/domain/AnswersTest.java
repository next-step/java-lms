package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUserTest;

public class AnswersTest {

	private static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
	private static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
	@Test
	@DisplayName("Answers 삭제하면 DeleteHistory를 반환한다")
	void delete(){
		Answer answer = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents");
		Answers answers = new Answers(List.of(answer));

		assertThat(answers.delete()).hasSize(1);
		assertThat(answer.isDeleted()).isTrue();
	}
}
