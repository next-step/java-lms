package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AnswersTest {
    @Test
    @DisplayName("답변 목록 중 질문자와 답변자 다른경우가 있으면 에러를 던짐")
    void delete_답변_중_다른답변자_존재() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answerList.add(new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));
        Answers answers = new Answers(answerList);

        assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI,LocalDateTime.now())).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    @DisplayName("답변 목록을 모두 삭제 상태로 바꾼 후 삭제 히스토리 리스트 리턴")
    void delete_성공() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        Answers answers = new Answers(answerList);

        assertThat(answers.delete(NsUserTest.JAVAJIGI,LocalDateTime.now()))
                .containsOnly(new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now()));



    }

}
