package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> delete() {
        return this.answers.stream()
                .map(answer -> {
                    try {
                        return answer.delete();
                    } catch (CannotDeleteException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
}
