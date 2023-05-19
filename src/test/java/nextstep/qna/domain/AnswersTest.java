package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AnswersTest {
    private static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    @Test
    void deleteAll_성공_질문_전체가_다_삭제됨() throws Exception {
        //given
        Answers answers = new Answers(Q1);
        Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        Answer A2 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents2");
        answers.add(A1);
        answers.add(A2);

        //when
        answers.deleteAll();

        //then
        assertTrue(A1.isDeleted());
        assertTrue(A2.isDeleted());
    }

    @Test
    void deleteAll_실패_답변자가_다르면_삭제_안됨() {
        //given
        Answers answers = new Answers(Q1);
        answers.add(new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2"));

        //when, then
        Assertions.assertThatThrownBy(answers::deleteAll)
                .isInstanceOf(CannotDeleteException.class);
    }
}