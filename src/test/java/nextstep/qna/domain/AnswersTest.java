package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswersTest {

  public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
      "Answers Contents1");
  public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1,
      "Answers Contents2");

  @DisplayName("삭제하려는 경우 답변들의 주인이 아니면 Exception을 던진다.")
  @Test
  public void delete_throwsException_ifNotOwner() {
    Answers answers = new Answers();

    assertAll(
        () -> answers.add(A1),
        () -> answers.add(A2),
        () -> assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class),
        () -> assertThatThrownBy(() -> answers.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
    );
  }

  @DisplayName("답변들의 주인이 아니면 Exception을 던져서 주인을 검증한다.")
  @Test
  public void validateAnswersOwner_throwsException_ifNotOwner() {
    Answers answers = new Answers();

    assertAll(
        () -> answers.add(A1),
        () -> answers.add(A2),
        () -> assertThatThrownBy(() -> answers.validateAnswersOwner(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class),
        () -> assertThatThrownBy(() -> answers.validateAnswersOwner(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
    );
  }
}
