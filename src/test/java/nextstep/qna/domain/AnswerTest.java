package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("작성자가 아닌 사람이 답변을 삭제하려고 하면 예외를 던진다")
    @Test
    void isNotOwner() {
        assertThatThrownBy(() -> A1.deleteAnswer(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("이 유저는 답변을 삭제할 권한이 없습니다");
    }

    @DisplayName("대답을 삭제하면 그 상태(deleted)가 true로 바뀐다")
    @Test
    void isDeleted() throws CannotDeleteException {
        A2.deleteAnswer(NsUserTest.SANJIGI);
        assertThat(A2.isDeleted()).isTrue();
    }

    @DisplayName("삭제이력을 추가해주는지 검증한다")
    @Test
    void toHistories() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(new DeleteHistory(ContentType.ANSWER, 1L, NsUserTest.SANJIGI, LocalDateTime.now()));
        deleteHistories.add(A1.deleteAnswer(NsUserTest.JAVAJIGI));

        assertThat(deleteHistories.size()).isEqualTo(2);
    }

}
