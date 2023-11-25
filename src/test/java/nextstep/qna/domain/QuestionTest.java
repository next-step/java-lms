package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
	public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
	public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

	private Question question;
	private Answer answer1;
	private Answer answer2;

	@BeforeEach
	public void init() {
		question = new Question(NsUserTest.JAVAJIGI, "title", "contents");
		answer1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
		answer2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
		question.addAnswer(answer1);
		question.addAnswer(answer2);
	}

	@Test
	@DisplayName("delete_질문 작성자와 다른 로그인 유저_throw exception")
	void 질문작성자_로그인유저_불일치() {
		assertThatThrownBy(() -> {
			question.delete(NsUserTest.SANJIGI);
		}).isInstanceOf(CannotDeleteException.class).hasMessage("질문을 삭제할 권한이 없습니다.");
	}

	@Test
	@DisplayName("delete_답변글이 없는 경우_삭제 가능")
	void 답변글이_없는_경우() {
		question = new Question(NsUserTest.JAVAJIGI, "title", "contents");
		assertThatNoException().isThrownBy(() -> {
			question.delete(NsUserTest.JAVAJIGI);
		});
		assertThat(question.isDeleted()).isTrue();
	}

	@Test
	@DisplayName("delete_질문 작성자, 답변 작성자, 로그인 유저가 같은 질문_질문 및 답변의 삭제 상태 true")
	void delete_answer() {
		assertThatNoException().isThrownBy(() -> {
			question.delete(NsUserTest.JAVAJIGI);
		});
		assertThat(question.isDeleted()).isTrue();
		assertThat(answer1.isDeleted()).isTrue();
		assertThat(answer2.isDeleted()).isTrue();
	}

	@Test
	@DisplayName("delete_답변을 2개 가진 질문_삭제 이력 size 3")
	void 삭제_이력() throws CannotDeleteException {
		assertThat(question.delete(NsUserTest.JAVAJIGI)).hasSize(3);
	}
}
