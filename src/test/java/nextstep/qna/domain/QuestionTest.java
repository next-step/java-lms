package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
  public Question question1;
  public Question question2;

  @BeforeEach
  public void setUp() {
    question1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    question2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
  }

  @Test
  @DisplayName("로그인 사용자와 질문 작성자가 다른 경우 CannotDeleteException throw")
  public void validateOwnerOfQuestion() {
    assertThatThrownBy(() -> question1.validateOwnerQuestion(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
  }

}
