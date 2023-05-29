package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    public static Answer A1;
    public static Answer A2;

    @BeforeEach
    public void 데이터_초기화() {
        A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "a1-contents");
        A2 = new Answer(NsUserTest.SANJIGI, Q2, "a2-contents");
    }

    @DisplayName("[삭제성공]질문의 작성자와 일치하는 경우")
    @Test
    public void 질문_삭제() throws Exception {
        assertThat(Q1.deleteQuestion(NsUserTest.JAVAJIGI))
                .isInstanceOf(DeleteHistory.class)
                .isEqualTo(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));
    }

    @DisplayName("[삭제실패]질문의 작성자와 불일치하는 경우")
    @Test
    public void 질문_삭제_예외() throws Exception {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("[삭제실패]질문 작성자 일치, 답변 작성자 불일치")
    @Test
    public void 질문_작성자_불일치_삭제_예외() throws Exception {
        A1 = new Answer(NsUserTest.SANJIGI, Q1, "a1-contents");
        Q1.addAnswer(A1);
        assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 질문_삭제후_상태확인() throws Exception {
        Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

}
