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

    public void deleteAnswers(NsUser nsUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(nsUser);
        }
    }

    public List<DeleteHistory> createAnswerDeleteHistory() {
        return answers.stream().map(Answer::writeDeleteHistory)
                .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
