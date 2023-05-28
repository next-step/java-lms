package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

  private Answer a1;
  private Answer a2;

  @BeforeEach
  public void setUp() {
    Question q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    a1 = new Answer(NsUserTest.JAVAJIGI, q1, "Answers Contents1");
    a2 = new Answer(NsUserTest.SANJIGI, q1, "Answers Contents2");
  }

  @DisplayName("답변을 삭제 한다.")
  @Test
  public void makeDeleted() {
    DeleteHistory deleteHistoryA1 = a1.delete(NsUserTest.JAVAJIGI);
    DeleteHistory deleteHistoryA2 = a2.delete(NsUserTest.SANJIGI);
    assertAll(
        () -> assertThat(a1.isDeleted()).isTrue(),
        () -> assertThat(deleteHistoryA1).isEqualTo(a1.toDeleteHistory()),
        () -> assertThat(a2.isDeleted()).isTrue(),
        () -> assertThat(deleteHistoryA2).isEqualTo(a2.toDeleteHistory())
    );
  }

  @DisplayName("삭제하려는 경우 답변의 주인이 아니면 Exception을 던진다.")
  @Test
  public void makeDeleted_throwException_ifNotOwner() {
    assertAll(
        () -> assertThatThrownBy(() -> a1.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class),
        () -> assertThatThrownBy(() -> a2.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
    );
  }

  @DisplayName("답변의 주인이 아니면 Exception을 던져서 주인을 검증한다.")
  @Test
  public void validateAnswerOwner_throwException_ifNotOwner() {
    assertAll(
        () -> assertThatThrownBy(() -> a1.validateAnswerOwner(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class),
        () -> assertThatThrownBy(() -> a2.validateAnswerOwner(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
    );
  }
}
