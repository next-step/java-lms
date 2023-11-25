package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
	public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
	public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

	@Test
	@DisplayName("delete_질문 작성자와 다른 로그인 유저_throw exception")
	void 질문작성자_로그인유저_불일치() {
		assertThatThrownBy(() -> {
			Q1.delete(NsUserTest.SANJIGI);
		}).isInstanceOf(CannotDeleteException.class).hasMessage("질문을 삭제할 권한이 없습니다.");
	}

	@Test
	@DisplayName("delete_답변글이 없는 경우_삭제 가능")
	void 답변글이_없는_경우() {
		assertThatNoException().isThrownBy(() -> {
			Q1.delete(NsUserTest.JAVAJIGI);
		});
		assertThat(Q1.isDeleted()).isTrue();
	}

	@Test
	@DisplayName("delete_질문 작성자, 답변 작성자, 로그인 유저가 같은 질문_질문 및 답변의 삭제 상태 true")
	void delete_answer() {
		Q1.addAnswer(AnswerTest.A1);
		Q1.addAnswer(AnswerTest.A3);

		assertThatNoException().isThrownBy(() -> {
			Q1.delete(NsUserTest.JAVAJIGI);
		});
		assertThat(Q1.isDeleted()).isTrue();
		assertThat(AnswerTest.A1.isDeleted()).isTrue();
		assertThat(AnswerTest.A3.isDeleted()).isTrue();
	}
}
