package nextstep.qna.domain;

import nextstep.qna.AnswerFixtures;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserFixtures;

import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;


public class QuestionTest {

    @Test
    public void 질문삭제권한_실패_테스트() {
        Question question = new Question(NsUserFixtures.JAVAJIGI, "title1", "contents1");
        Assertions.assertThatThrownBy(() -> question.delete(NsUserFixtures.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }


    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하며 삭제 히스토리가 추가된다")
    public void deletett() throws CannotDeleteException {
        Question question = new Question(NsUserFixtures.JAVAJIGI, "title1", "contents1");
        DeleteHistorys historys = question.delete(NsUserFixtures.JAVAJIGI);

        List<DeleteHistory> sut = historys.toList();
        assertThat(sut.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("답변이 없는 경우 삭제 가능하며 삭제 히스토리가 추가된다")
    public void deleteWhenAnswerEmpty() throws CannotDeleteException {
        Question question = new Question(NsUserFixtures.JAVAJIGI, "title1", "contents1");
        DeleteHistorys historys = question.delete(NsUserFixtures.JAVAJIGI);

        List<DeleteHistory> sut = historys.toList();
        assertThat(sut).hasSize(1);
    }



    @Test
    @DisplayName("답변과 질문 삭제 테스트")
    public void deleteHistoryTest() throws CannotDeleteException {
        Question question = new Question(NsUserFixtures.JAVAJIGI, "title1", "contents1");
        question.addAnswer(AnswerFixtures.A1);

        DeleteHistorys historys = question.delete(NsUserFixtures.JAVAJIGI);
        List<DeleteHistory> deleteHistoryList = historys.toList();
        List<ContentType> types = deleteHistoryList.stream().map(DeleteHistory::getContentType).collect(Collectors.toList());
        assertThat(types).contains(ContentType.QUESTION, ContentType.ANSWER);

    }


    @Test
    public void delete() {
        Question question = new Question(NsUserFixtures.JAVAJIGI, "title1", "contents1");
        question.delete(NsUserFixtures.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    
}
