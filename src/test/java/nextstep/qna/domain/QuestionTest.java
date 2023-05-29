package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

  private Question q1;
  private Question q2;

  @BeforeEach
  public void setUp() {
    q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
  }

  @DisplayName("질문을 삭제한다.")
  @Test
  public void delete() {
    List<DeleteHistory> deleteHistoriesQ1 = q1.delete(NsUserTest.JAVAJIGI);
    List<DeleteHistory> deleteHistoriesQ2 = q2.delete(NsUserTest.SANJIGI);
    assertAll(
        () -> assertThat(deleteHistoriesQ1.get(0)).isEqualTo(q1.toDeleteHistory()),
        () -> assertThat(q1.isDeleted()).isTrue(),
        () -> assertThat(deleteHistoriesQ2.get(0)).isEqualTo(q2.toDeleteHistory()),
        () -> assertThat(q2.isDeleted()).isTrue()
    );
  }

  @DisplayName("질문의 주인이 아니면 Exception을 던진다.")
  @Test
  public void delete_throwException_ifNotOwner() {
    assertAll(
        () -> assertThatThrownBy(() -> q1.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class),
        () -> assertThatThrownBy(() -> q2.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
    );
  }
}
