package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer){
        this.answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        // 로그인한 유저 외에 다른 사용자가 작성한 답변이 있다면 삭제할 수 없음.
        validateHasOtherUserAnswers(loginUser);

        // 답변들을 삭제한다.
        for (Answer answer : this.answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }

        return deleteHistories;
    }
    public void validateHasOtherUserAnswers(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            answer.hasOtherUserAnswer(answer.isOwner(loginUser));
        }
    }
}
