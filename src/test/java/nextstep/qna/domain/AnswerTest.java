package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

  public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
      "Answers Contents1");
  public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1,
      "Answers Contents2");

  @DisplayName("답변을 삭제 한다.")
  @Test
  public void makeDeleted() {
    DeleteHistory deleteHistoryA1 = A1.delete(NsUserTest.JAVAJIGI);
    DeleteHistory deleteHistoryA2 = A2.delete(NsUserTest.SANJIGI);
    assertAll(
        () -> assertThat(A1.isDeleted()).isTrue(),
        () -> assertThat(deleteHistoryA1).isEqualTo(A1.toDeleteHistory()),
        () -> assertThat(A2.isDeleted()).isTrue(),
        () -> assertThat(deleteHistoryA2).isEqualTo(A2.toDeleteHistory())
    );
  }

  @DisplayName("삭제하려는 경우 답변의 주인이 아니면 Exception을 던진다.")
  @Test
  public void makeDeleted_throwException_ifNotOwner() {
    assertAll(
        () -> assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class),
        () -> assertThatThrownBy(() -> A2.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
    );
  }

  @DisplayName("답변의 주인이 아니면 Exception을 던져서 주인을 검증한다.")
  @Test
  public void validateAnswerOwner_throwException_ifNotOwner() {
    assertAll(
        () -> assertThatThrownBy(() -> A1.validateAnswerOwner(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class),
        () -> assertThatThrownBy(() -> A2.validateAnswerOwner(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
    );
  }
}
