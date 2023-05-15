package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");
    public static final NsUser requestUser = NsUserTest.JAVAJIGI;

    @Test
    @DisplayName("삭제 - 답변이 없는 경우")
    void delete() throws CannotDeleteException {
        // when
        List<DeleteHistory> deleteHistories = Q1.delete(requestUser);
        DeleteHistory expectedHistory =
                new DeleteHistory(
                        ContentType.QUESTION,
                        Q1.getId(),
                        NsUserTest.JAVAJIGI, deleteHistories.get(0).deletedTime()
                );

        // then
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(deleteHistories.size()).isEqualTo(1);
        assertThat(deleteHistories.get(0)).isEqualTo(expectedHistory);
    }

    @Test
    @DisplayName("삭제 - 모든 답변자와 질문자가 동일한 경우")
    void delete2() throws CannotDeleteException {
        // given
        Q1.addAnswer(A1);

        // when
        List<DeleteHistory> deleteHistories = Q1.delete(requestUser);

        // then
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistories.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("삭제 - 답변자가 질문자와 다른 경우 예외")
    void delete3() {
        // given
        Q1.addAnswer(A2);

        // when
        assertThatThrownBy(() -> Q1.delete(requestUser))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }


    @Test
    @DisplayName("삭제 - 요청자와 질문자가 다른 경우 예외")
    void delete4() {
        // given
        NsUser requestUser2 = NsUserTest.SANJIGI;

        // when
        assertThatThrownBy(() -> Q1.delete(requestUser2))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");



    }
}
