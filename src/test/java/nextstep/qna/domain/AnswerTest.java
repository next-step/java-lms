package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("담변 삭제 권한 체크 - 질문자와 답변글의 모든 답변자 같은 경우")
    void checkAnswerDeletion_WithAllSameLoginUser(){
        //given

        //when
        boolean checkAnswerDeletion1 = A1.validateDelete(NsUserTest.JAVAJIGI);
        boolean checkAnswerDeletion2 = A2.validateDelete(NsUserTest.SANJIGI);

        //then
        Assertions.assertThat(checkAnswerDeletion1).isEqualTo(true);
        Assertions.assertThat(checkAnswerDeletion2).isEqualTo(true);
    }

    @Test
    @DisplayName("담변 삭제 권한 체크 - 질문자와 답변글의 모든 답변자 같은 않은 경우")
    void checkAnswerDeletion_WithDifferentLoginUser(){
        //given
        NsUser loginUser = NsUserTest.SANJIGI;

        //when
        boolean checkAnswerDeletion = A1.validateDelete(loginUser);

        //then
        Assertions.assertThat(checkAnswerDeletion).isEqualTo(false);
    }
}
