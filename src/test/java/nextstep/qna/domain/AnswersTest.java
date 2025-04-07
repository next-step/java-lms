package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswersTest {
  @Test
  @DisplayName("모든 답변이 해당 사용자가 작성한 경우 유효성 검증을 성공한다.")
  void validateAllOwnedBy_success() {
    Answers group = new Answers();
    group.add(new Answer(NsUserTest.JAVAJIGI, new Question(), "답변1"));
    group.add(new Answer(NsUserTest.JAVAJIGI, new Question(), "답변2"));

    assertThatCode(() -> group.validateAllOwnedBy(NsUserTest.JAVAJIGI))
        .doesNotThrowAnyException();
  }

  @Test
  @DisplayName("답변 중 다른 작성자가 있는 경우 CannotDeleteException 예외가 발생한다.")
  void validateAllOwnedBy_fail() {
    Answers group = new Answers();
    group.add(new Answer(NsUserTest.JAVAJIGI, new Question(), "답변1"));
    group.add(new Answer(NsUserTest.SANJIGI, new Question(),"답변2"));

    assertThatThrownBy(() -> group.validateAllOwnedBy(NsUserTest.JAVAJIGI))
        .isInstanceOf(CannotDeleteException.class)
        .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
  }

  @Test
  @DisplayName("deleteBy: 모든 답변이 작성자와 일치하면 삭제되고 이력이 반환된다.")
  void deleteBy_success() throws CannotDeleteException {
    Answers group = new Answers();
    Answer a1 = new Answer(NsUserTest.JAVAJIGI, new Question(), "답변1");
    Answer a2 = new Answer(NsUserTest.JAVAJIGI, new Question(), "답변2");
    group.add(a1);
    group.add(a2);

    List<DeleteHistory> histories = group.deleteBy(NsUserTest.JAVAJIGI);

    assertThat(histories).hasSize(2);
    assertThat(a1.isDeleted()).isTrue();
    assertThat(a2.isDeleted()).isTrue();
  }

  @Test
  @DisplayName("deleteBy: 다른 사람이 작성한 답변이 있으면 CannotDeleteException 예외가 발생한다.")
  void deleteBy_fail() {
    Answers group = new Answers();
    group.add(new Answer(NsUserTest.JAVAJIGI, new Question(), "답변1"));
    group.add(new Answer(NsUserTest.SANJIGI, new Question(), "답변2"));

    assertThatThrownBy(() -> group.deleteBy(NsUserTest.JAVAJIGI))
        .isInstanceOf(CannotDeleteException.class);
  }

}
