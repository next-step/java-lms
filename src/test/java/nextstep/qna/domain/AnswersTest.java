package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswersTest {

  private Answer a1;
  private Answer a2;

  @BeforeEach
  public void setUp() {
    Question q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    Question q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    a1 = new Answer(NsUserTest.JAVAJIGI, q1, "Answers Contents1");
    a2 = new Answer(NsUserTest.SANJIGI, q2, "Answers Contents2");
  }

  @DisplayName("삭제하려는 경우 답변들의 주인이 아니면 Exception을 던진다.")
  @Test
  public void delete_throwsException_ifNotOwner() {
    Answers answers = new Answers();

    assertAll(
        () -> answers.add(a1),
        () -> answers.add(a2),
        () -> assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class),
        () -> assertThatThrownBy(() -> answers.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
    );
  }
}
