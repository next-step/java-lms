package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");


    @Test
    @DisplayName("[성공] 자신이 작성한 질문을 삭제한다.")
    void 답변_삭제() {
        assertThatNoException().isThrownBy(() -> {
            Q1.delete(NsUserTest.JAVAJIGI);
        });
    }

    @Test
    @DisplayName("[성공] 자신이 작성한 질문을 삭제하면 질문과 답변에 대한 히스토리 리스트를 반환한다.")
    void 답변_삭제_히스토리_반환() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        assertThat(Q1.delete(NsUserTest.JAVAJIGI).get())
                .containsExactly(
                        new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI, LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now())
                );
    }

    @Test
    @DisplayName("[실패] 자신이 생성하지 않은 질문을 삭제하려는 경우 CannotDeleteException 예외가 발생한다.")
    void 답변_삭제_불가능() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(NsUserTest.SANJIGI));
    }

}
