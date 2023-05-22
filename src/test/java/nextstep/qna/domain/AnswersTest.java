package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AnswersTest {

    private Answers answersMine;
    private Answers answersOthers;

    @BeforeEach
    void setUp() {
        List<Answer> answerMyList = new ArrayList<>();
        answerMyList.add(AnswerTest.A1);
        answerMyList.add(AnswerTest.A1);

        answersMine = new Answers(answerMyList);

        List<Answer> answerOtherList = new ArrayList<>();
        answerOtherList.add(AnswerTest.A2);
        answerOtherList.add(AnswerTest.A2);

        answersOthers = new Answers(answerMyList);
    }


    @Test
    void answers_생성() {
        //assert
        assertThat(answersMine).isNotNull();
    }

    @Test
    void answers_남의답변_없을경우_삭제() {
        //assert
        assertThatNoException()
                .isThrownBy(() -> answersMine.deleteAnswers(NsUserTest.JAVAJIGI));
    }

    @Test
    void answers_남의답변_있는경우_삭제() {
        //assert
        assertThatCode(() -> answersOthers.deleteAnswers(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void answers_삭제로그_기록() throws CannotDeleteException {
        //act
        List<DeleteHistory> deleteHistories = answersMine.deleteAnswers(NsUserTest.JAVAJIGI);

        //assert
        assertThat(deleteHistories).hasSize(2);
        assertThat(deleteHistories.get(0).getDeleteUser()).isEqualTo(NsUserTest.JAVAJIGI);
        assertThat(deleteHistories.get(0).getContentType()).isEqualTo(ContentType.ANSWER);
        assertThat(deleteHistories.get(1).getDeleteUser()).isEqualTo(NsUserTest.JAVAJIGI);
        assertThat(deleteHistories.get(1).getContentType()).isEqualTo(ContentType.ANSWER);
    }
}
