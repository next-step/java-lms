package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    private Question question1;
    private Question question2;

    @BeforeEach
    public void beforeEach() {
        question1 = new Question(NsUserTest.JAVAJIGI, "제목1", "내용1");
        question2 = new Question(NsUserTest.SANJIGI, "제목2", "내용2");
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 예외가 발생하지 않는지 확인")
    @Test
    void delete_로그인_사용자와_질문한_사람이_같은_경우() {
        assertThatCode(() -> question1.delete(NsUserTest.JAVAJIGI))
                .doesNotThrowAnyException();
    }

    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 CannotDeleteException 예외가 발생하는지 확인")
    @Test
    void delete_로그인_사용자와_질문한_사람이_다른_경우() {
        assertThatThrownBy(() -> question1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문자와 답변글의 모든답변자가 같은 경우 예외가 발생하지 않는지 확인")
    @Test
    void delete_질문자와_모든_답변자가_같은_경우() {
        question1.addAnswer(AnswerTest.A1);
        assertThatCode(() -> question1.delete(NsUserTest.JAVAJIGI))
                .doesNotThrowAnyException();
    }

    @DisplayName("질문자와 답변글의 모든답변자가 다른 경우 CannotDeleteException 예외가 발생하는지 확인")
    @Test
    void delete_질문자와_모든_답변자가_다른_경우() {
        question2.addAnswer(AnswerTest.A1);
        assertThatThrownBy(() -> question2.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
