package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.*;
import static org.assertj.core.api.Assertions.*;

class AnswersTest {
	private static final Answers sameWriterWithQuestion = new Answers(List.of(A1, A3));
	private static final Answers notSameWriterWithQuestion = new Answers(List.of(A3, A4));


	@Test
	@DisplayName("delete_로그인 사용자와 답변 작성자가 다른 Answer 존재_throw exception")
	void 답변글작성자_loginUser_불일치() {
		assertThatThrownBy(() -> {
			sameWriterWithQuestion.delete(NsUserTest.SANJIGI);
		}).isInstanceOf(CannotDeleteException.class).hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
	}

	@Test
	@DisplayName("delete_로그인 사용자, 질문 작성자, 답변 작성자 모두 일치_삭제 상태 true")
	void 답변글작성자_loginUser_질문글작성자_일치() {
		assertThatNoException().isThrownBy(() -> {
			sameWriterWithQuestion.delete(NsUserTest.JAVAJIGI);
		});
		assertThat(A1.isDeleted()).isTrue();
		assertThat(A3.isDeleted()).isTrue();
	}
}