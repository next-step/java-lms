package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NextStepUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
  public Answer a1;
  public Answer a2;

  @BeforeEach
  public void setUp() {
    a1 = new Answer(NextStepUserTest.JAVAJIGI, new Question(NextStepUserTest.JAVAJIGI, "title1", "contents1"), "Answers Contents1");
    a2 = new Answer(NextStepUserTest.SANJIGI, new Question(NextStepUserTest.JAVAJIGI, "title1", "contents1"), "Answers Contents2");
  }

  @Test
  @DisplayName("다른 사용자가 답변글을 입력한 경우 CannotDeleteException throw")
  public void validate_답변글_작성자() {
    assertThatThrownBy(() -> a1.validateIsOwner(NextStepUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
  }
}
