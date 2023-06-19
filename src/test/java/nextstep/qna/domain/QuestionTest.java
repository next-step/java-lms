package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");


    @Test
    public void delete_성공_내가_쓴_글() throws Exception {
        //given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.delete(NsUserTest.JAVAJIGI);

        //then
        Assertions.assertThat(question.isDeleted()).isTrue();
    }

    @Test
    public void delete_실패_다른_사람이_쓴_글() throws Exception {
        //given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        //then
        Assertions.assertThatThrownBy(()->question.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);

    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        //given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI,question,"test"));

        question.delete(NsUserTest.JAVAJIGI);

        //then
        Assertions.assertThat(question.isDeleted()).isTrue();
    }

    @Test
    public void delete_실패_답변_중_다른_사람이_쓴_글() throws Exception {
        //given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(NsUserTest.SANJIGI,question,"test"));

        //then
        Assertions.assertThatThrownBy(()->question.delete(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
