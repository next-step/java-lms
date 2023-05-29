package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("Question 정상 삭제")
    void deleteNormal() {
        //given when
        Assertions.assertDoesNotThrow(() -> {
            Q1.delete(NsUserTest.JAVAJIGI);
        });

        //then
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("Question 삭제 테스트 에러 발생")
    void deleteError() {
        Assertions.assertThrows(CannotDeleteException.class, () -> {
            Q1.delete(NsUserTest.SANJIGI);
        });
    }
}
