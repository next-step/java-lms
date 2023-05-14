package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
  public Question question;

  @BeforeEach
  public void setUp() {
    question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
  }

  @Test
  @DisplayName("로그인 사용자와 질문 작성자가 다른 경우 CannotDeleteException throw")
  public void validateOwnerOfQuestion() {
    assertThatThrownBy(() -> question.validateOwnerQuestion(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
  }

  @Test
  @DisplayName("답변 삭제 테스트")
  public void deleteQuestion() throws CannotDeleteException {
    question.deleteQuestion(NsUserTest.JAVAJIGI);

    Assertions.assertAll(
            () -> assertThat(question.isDeleted()).isTrue(),
            () -> assertThat(question.getWriter().getUserId()).isEqualTo("javajigi")
    );
  }
}
