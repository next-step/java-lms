package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static Answer A1;
    public static Answer A2;

    @BeforeEach
    public void 데이터_초기화() {
        Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
        A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "a1-contents");
        A2 = new Answer(NsUserTest.SANJIGI, Q2, "a2-contents");
    }

    @Test
    public void create() throws Exception {
        assertThat(new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"))
                .isNotNull()
                .isInstanceOf(Answer.class);
    }

    @DisplayName("답변 작성자 일치여부 확인")
    @Test
    public void 작성자_확인() throws Exception {
        assertThat(A1.isOwner(NsUserTest.JAVAJIGI)).isTrue();
        assertThat(A1.isOwner(NsUserTest.SANJIGI)).isFalse();
    }

    @DisplayName("답변 삭제 후, 반환 값 확인")
    @Test
    public void 답변_삭제() throws Exception {
        assertThat(A1.delete()).isInstanceOf(DeleteHistory.class)
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
