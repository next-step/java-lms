package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("로그인 유저가 질문 작성자가 아니면 예외를 던진다.")
    @Test
    void test1() throws Exception {
        assertThatThrownBy(() -> {
            Q1.isOwner(NsUserTest.SANJIGI);
        })
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("답변들 중 다른 사람이 쓴 답변이 있다면 예외를 던진다.")
    @Test
    void test2() throws Exception {
        Q1.addAnswer(new Answer(NsUserTest.JAVAJIGI, Q1, "test1"));
        Q1.addAnswer(new Answer(NsUserTest.SANJIGI, Q1, "test2"));

        assertThatThrownBy(() -> {
            Q1.checkUserOfAnswers(NsUserTest.SANJIGI);
        })
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("질문 삭제 시 상태를 질문의 상태를 삭제로 변경하고 삭제 히스토리에 넣는다")
    @Test
    void test3() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        List<DeleteHistory> results = Q1.delete(now);

        assertThat(results)
            .contains(new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI, now))
            .contains(new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, now));
    }
}
