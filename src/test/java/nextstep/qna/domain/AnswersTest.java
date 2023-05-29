package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    static Answers answersWithOther;
    static Answers answersOnlyOwner;

    @BeforeEach
    void init() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.QUESTION_1, "Answers Contents1"));
        answerList.add(new Answer(NsUserTest.SANJIGI, QuestionTest.QUESTION_1, "Answers Contents2"));
        answersWithOther = new Answers(answerList);

        List<Answer> answerList2 = new ArrayList<>();
        answerList2.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.QUESTION_1, "Answers Contents1"));
        answersOnlyOwner = new Answers(answerList2);
    }

    @Test
    void addAnswerTest() {
        answersWithOther.addAnswer(new Answer(NsUserTest.JAVAJIGI, QuestionTest.QUESTION_1, "Answers Contents3"));

        var expectAnswerSize = 3;

        assertThat(answersWithOther.AnswerSize()).isEqualTo(expectAnswerSize);
    }

    @Test
    void otherOwnerCheckTest_다른작성자답변_존재() {
        assertThat(answersWithOther.otherOwnerCheck(NsUserTest.JAVAJIGI)).isFalse();
        assertThat(answersWithOther.otherOwnerCheck(NsUserTest.SANJIGI)).isFalse();
        assertThat(answersOnlyOwner.otherOwnerCheck(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    void otherOwnerCheckTest_다른작성자답변_미존재() {
        assertThat(answersOnlyOwner.otherOwnerCheck(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    void deleteTest_삭제성공_작성자_본인o() throws CannotDeleteException {
        var deleteHistoryList = answersOnlyOwner.delete(NsUserTest.JAVAJIGI);
        assertThat(deleteHistoryList).hasSize(1);
        assertThat(deleteHistoryList.get(0)).isInstanceOf(DeleteHistory.class);
    }

    @Test
    void deleteTest_삭제실패_작성자_본인x() {
        assertThatThrownBy(() -> answersWithOther.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
