package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 질문_삭제_권한없음__작성자_본인_아님(){
        assertThatThrownBy(() -> {
            Q1.checkDeletePermission(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문_삭제_권한없음__다른_사람_답변_존재(){
        assertThatThrownBy(() -> {
            Q1.checkAnswerOwner(
                    new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"),
                    NsUserTest.SANJIGI
            );
        }).isInstanceOf(CannotDeleteException.class);
    }



}
