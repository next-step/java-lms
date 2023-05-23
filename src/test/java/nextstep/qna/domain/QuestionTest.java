package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1", new Answers());
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2", new Answers());

    public static final Question Q3 = new Question(NsUserTest.SANJIGI, "title3", "contents3",
            new Answers(Lists.newArrayList(AnswerTest.A1, AnswerTest.A1)));
    public static final Question Q4 = new Question(NsUserTest.SANJIGI, "title4", "contents4",
            new Answers(Lists.newArrayList(AnswerTest.A2, AnswerTest.A2)));

    @Test
    void test_삭제_남의글() throws CannotDeleteException {
        //assert
        assertThatCode(() -> Q1.deleteQuestion(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void test_삭제_내글() throws CannotDeleteException {
        //act
        Q1.deleteQuestion(NsUserTest.JAVAJIGI);

        //assert
        assertThat(Q1.isDeleted()).isTrue();

    }

    @Test
    void test_삭제_다른사람_답변있음() throws CannotDeleteException {
        assertThatCode(() -> Q3.deleteQuestion(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

    }

    @Test
    void test_삭제_다른사람_답변없음() throws CannotDeleteException {
        //act
        Q4.deleteQuestion(NsUserTest.SANJIGI);

        //assert
        assertThat(Q4.isDeleted()).isTrue();
    }

}
