package nextstep.qna.domain;

import java.util.List;
import java.util.Map;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    public static final Answer A1 = new Answer(1L, NsUserTest.JAVAJIGI, Q1, "contents1");

    @BeforeEach
    void init(){
        Q1.getAnswers().clear();
    }

    @Test
    @DisplayName("질문 삭제 권한 체크 - 로그인 사용자와 질문한 사람이 같은 경우")
    void checkQuestionDeletion_WithSameLoginUser(){
        //given
        NsUser loginUser = NsUserTest.JAVAJIGI;

        //when
        boolean checkDeletion = Q1.isDeletionAvailable(loginUser);

        //then
        Assertions.assertThat(checkDeletion).isEqualTo(true);
    }

    @Test
    @DisplayName("질문 삭제 권한 체크 - 로그인 사용자와 질문한 사람이 다른 경우")
    void checkQuestionDeletion_WithDifferentLoginUser(){
        //given
        NsUser loginUser = NsUserTest.JAVAJIGI;

        //when
        boolean checkDeletion = Q2.isDeletionAvailable(loginUser);

        //then
        Assertions.assertThat(checkDeletion).isEqualTo(false);
    }

    @Test
    @DisplayName("담변 삭제 권한 체크 - 질문자와 답변글의 모든 답변자 같은 경우")
    void checkAnswerDeletion_WithAllSameLoginUser(){
        //given
        NsUser loginUser = NsUserTest.JAVAJIGI;
        Q1.addAnswer(A1);

        //when
        boolean checkAnswerDeletion = Q1.isAnswerDeletionAvailable(loginUser);

        //then
        Assertions.assertThat(checkAnswerDeletion).isEqualTo(true);
    }

    @Test
    @DisplayName("담변 삭제 권한 체크 - 질문자와 답변글의 모든 답변자 같은 않은 경우")
    void checkAnswerDeletion_WithDifferentLoginUser(){
        //given
        NsUser loginUser = NsUserTest.SANJIGI;
        Q1.addAnswer(A1);

        //when
        boolean checkAnswerDeletion = Q1.isAnswerDeletionAvailable(loginUser);

        //then
        Assertions.assertThat(checkAnswerDeletion).isEqualTo(false);
    }

    @Test
    @DisplayName("질문 삭제 - 로그인 사용자와 질문한 사람이 다른 경우")
    void deleteQuestion_WithDifferentLoginUser() throws CannotDeleteException {
        //given
        NsUser loginUser = NsUserTest.SANJIGI;
        Q1.addAnswer(A1);

        //when
        ThrowingCallable callable = () -> Q1.delete(loginUser);


        //then
        Assertions.assertThatThrownBy(
            callable
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문 삭제 - 질문도 같이")
    void deleteQuestion_IncludingAnswers() throws CannotDeleteException {
        //given
        NsUser loginUser = NsUserTest.JAVAJIGI;
        Q1.addAnswer(A1);

        //when
        List<DeleteHistory> result = Q1.delete(loginUser);

        //then
        Assertions.assertThat(result).hasSize(2);
    }
}
