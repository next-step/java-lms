package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean deleteAnswers(NsUser nsUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            checkIsOwner(nsUser, answer);
        }

        for (Answer answer : answers) {
            answer.delete(nsUser);
        }
        return true;
    }

    public List<DeleteHistory> createAnswerDeleteHistory() {
        return answers.stream().map(Answer::writeDeleteHistory)
                .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    private void checkIsOwner(NsUser nsUser, Answer answer) throws CannotDeleteException {
        if (!answer.isOwner(nsUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
