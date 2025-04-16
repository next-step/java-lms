package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class QuestionTest {

    Question question1;
    Answer answer1;
    Answer answer2;

    @BeforeEach
    void setUp() {
        question1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        answer1 = new Answer(NsUserTest.JAVAJIGI, question1, "Answers Contents1");
        answer2 = new Answer(NsUserTest.JAVAJIGI, question1, "Answers Contents2");
        question1.addAnswer(answer1);
        question1.addAnswer(answer2);
    }

    @Test
    void 질문자_불일치() {
        assertNotEquals(question1.getWriter(), NsUserTest.SANJIGI);
    }

    @Test
    void 질문자_불일치시_예외_발생() {
        assertThatThrownBy(
            () -> question1.deleteBy(NsUserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문자_답변자_모두_같을시_답변_삭제() {
        question1.deleteBy(NsUserTest.JAVAJIGI);
        assertAll(
            () -> assertTrue(question1.isDeleted()),
            () -> assertTrue(answer1.isDeleted()),
            () -> assertTrue(answer2.isDeleted())
        );
    }
}
