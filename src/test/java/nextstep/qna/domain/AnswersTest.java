package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

    @Test
    @DisplayName(value = "Answers 생성 검사")
    void test1() {
        Answers answers = new Answers();
        answers.add(A1);
        answers.add(A2);

        Answers AS1 = new Answers(Arrays.asList(A1, A2));

        assertThat(answers).isEqualTo(AS1);
    }


    @Test
    @DisplayName(value = "삭제 후 삭제 이력 정보 확인")
    void test2() throws CannotDeleteException {
        // given
        Answers answers = new Answers(Arrays.asList(A1));
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now());

        // when
        List<DeleteHistory> deleteHistoryList = answers.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(deleteHistoryList).contains(deleteHistory);
    }

    @Test
    @DisplayName(value = "로그인 유저와 다른 사람이 답변을 남겼을 경우 삭제 불가")
    void test3() {
        Answers answers = new Answers(Arrays.asList(A1, A2));

        assertThatThrownBy(() -> {
            answers.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
