package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAll(NsUser writer) {
        return answers.stream()
                .map(s -> {
                    try {
                        return s.delete(writer);
                    } catch (CannotDeleteException e) {
                        throw new CannotDeleteException(e.getMessage());
                    }
                }).collect(Collectors.toList());
    }
}
