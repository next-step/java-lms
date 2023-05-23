package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

  public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
  public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

  @DisplayName("질문을 삭제한다.")
  @Test
  public void delete() {
    List<DeleteHistory> deleteHistoriesQ1 = Q1.delete(NsUserTest.JAVAJIGI);
    List<DeleteHistory> deleteHistoriesQ2 = Q2.delete(NsUserTest.SANJIGI);
    assertAll(
        () -> assertThat(deleteHistoriesQ1.get(0)).isEqualTo(Q1.toDeleteHistory()),
        () -> assertThat(Q1.isDeleted()).isTrue(),
        () -> assertThat(deleteHistoriesQ2.get(0)).isEqualTo(Q2.toDeleteHistory()),
        () -> assertThat(Q2.isDeleted()).isTrue()
    );
  }

  @DisplayName("질문의 주인이 아니면 Exception을 던진다.")
  @Test
  public void delete_throwException_ifNotOwner() {
    assertAll(
        () -> assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class),
        () -> assertThatThrownBy(() -> Q2.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
    );
  }
}
