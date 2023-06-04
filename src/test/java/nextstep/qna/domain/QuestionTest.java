package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static Question Q1 = new Question(3L, NsUserTest.JAVAJIGI, new QuestionContents("title1", "contents1"));
    public static Question Q2 = new Question(4L, NsUserTest.SANJIGI, new QuestionContents("title2", "contents2"));

    @BeforeEach
    void setUp() {
        Q1 = new Question(3L, NsUserTest.JAVAJIGI, new QuestionContents("title1", "contents1"));
        Q2 = new Question(4L, NsUserTest.SANJIGI, new QuestionContents("title2", "contents2"));
    }

    @Test
    @DisplayName("[요구사항_1] 로그인 사용자와 질문한 사람이 다를 경우 삭제할 수 없다는 Exception을 던진다.")
    void 요구사항_1() {
        // given : 로그인 진행 (JAVAJIGI)
        NsUser loginUser = NsUserTest.JAVAJIGI;

        // when & then : JAVAJIGI가 SANJIGI의 질문을 삭제 하려 하면 CannotDeleteException throw
        assertThatThrownBy(() -> Q2.delete(loginUser.getId()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("[요구사항_2] 질문자와 답변자가 다른 경우 삭제할 수 없다는 Exception을 던진다.")
    void 요구사항_2() {
        // when : 질문자 SANJIGI의 글에 JAVAJIGI가 답변을 남김
        Q2.getAnswers().addAnswer(new Answer(1L, NsUserTest.JAVAJIGI, Q2, new AnswerContents("happy")));

        // then : 질문자 SANJIGI가 질문을 지우려고 하면 CannotDeleteException throw
        assertThatThrownBy(() -> Q2.delete(NsUserTest.SANJIGI.getId()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("[요구사항_3] 요구사항 1, 2에 제시된 예외에 걸리지 않는 경우 성공적으로 삭제가 이루어진다.")
    void 요구사항_3() throws CannotDeleteException {
        // given : 로그인 유저이자 질문자인 SANJIGI의 글에 자기 자신이 답변을 남김
        NsUser loginUser = NsUserTest.SANJIGI;
        Q2.getAnswers().addAnswer(new Answer(1L, loginUser, Q2, new AnswerContents("happy")));

        // when : SANJIGI가 자신의 질문 삭제
        Q2.delete(loginUser.getId());

        // then : Q2가 정말로 삭제되었는지 확인
        assertThat(Q2.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("[요구사항_4] 질문 삭제 시, 질문과 답변 삭제 이력을 성공적으로 제공한다.")
    void 요구사항_4() throws CannotDeleteException {
        // given : 요구사항_3과 같은 세팅
        NsUser loginUser = NsUserTest.SANJIGI;
        Q2.getAnswers().addAnswer(new Answer(1L, loginUser, Q2, new AnswerContents("happy")));
        Q2.delete(loginUser.getId());

        // then : 질문과 답변 삭제 이력의 크기가 2개가 나오는지 확인 (질문 1, 답변 1)
        assertThat(Q2.toQuestionAndAnswersHistories().getDeleteHistories().size()).isEqualTo(2);
    }
}
