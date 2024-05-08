package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.")
    void DELETE_IF_SAME_LOG_USER_AND_QUESTION_USER() {
        NsUser logUser = NsUserTest.SANJIGI;
        assertThatThrownBy(() -> Q1.delete(logUser))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제 시 데이터 상태를 삭제 상태(true)로 변경한다")
    void DELETE_AFTER_DELETED_STATUS_IS_TRUE() throws CannotDeleteException {
        NsUser logUser = NsUserTest.JAVAJIGI;
        Q1.delete(logUser);
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("Wrtier가 삭제 시 답변이 없으면 삭제 가능하다.")
    void QUESTION_DO_NOT_HAVE_ANSWERS_THEN_DELETABLE() throws CannotDeleteException {
        NsUser logUser = NsUserTest.JAVAJIGI;
        List<DeleteHistory> deleteHistories = List.of(
            new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));
        assertThat(Q1.delete(logUser)).isEqualTo(deleteHistories);
    }
}
