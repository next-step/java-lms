package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    public static Answer A1;
    public static Answer A2;

    @BeforeEach
    public void setup() {
        A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "answer-contents1");
        A2 = new Answer(NsUserTest.SANJIGI, Q2, "answer-contents2");
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같지 않을 경우 예외")
    public void question_질문삭제예외() throws Exception {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제")
    public void question_질문삭제확인() throws Exception {
        assertThat(Q1.deleteQuestion(NsUserTest.JAVAJIGI))
                .isInstanceOf(DeleteHistory.class)
                .isEqualTo(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("질문자와 답변글의 모든답변자 같은 경우 삭제")
    public void question_질문자답변자삭제() throws Exception {
        Q1.addAnswer(A1);
        assertThat(Q1.deleteAnswers(NsUserTest.JAVAJIGI)).isNotNull();
    }

    @Test
    @DisplayName("질문을 삭제 후 삭제상태 확인")
    public void question_질문삭제상태확인() throws Exception {
        Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

}
