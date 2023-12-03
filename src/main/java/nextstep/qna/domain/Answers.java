package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public List<DeleteHistory> writeDeleteAnswersHistory(NsUser writer) throws CannotDeleteException {
        List<DeleteHistory> deleteAnswersHistories = new ArrayList<>();
        for (Answer answer : this.answers) {
            deleteAnswersHistories.add(writeDeleteAnswerHistory(answer, writer));
        }
        return deleteAnswersHistories;
    }

    private DeleteHistory writeDeleteAnswerHistory(Answer answer, NsUser writer) throws CannotDeleteException {
        if (!answer.isOwner(writer)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        return answer.writeDeleteAnswerHistory();
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
