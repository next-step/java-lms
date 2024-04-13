package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

import static nextstep.qna.ExceptionMessage.DIFFERENT_WRITER_OF_QUESTION_AND_WRITER_OF_ANSWER;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> deleteByUser(NsUser user) throws CannotDeleteException {
        validateDeletableAnswers(user);

        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            deleteHistories.add(answer.deleteByUser(user));
        }

        return deleteHistories;
    }

    public void validateDeletableAnswers(NsUser user) throws CannotDeleteException {
        if (!isDeletableByWriter(user)) {
            throw new CannotDeleteException(DIFFERENT_WRITER_OF_QUESTION_AND_WRITER_OF_ANSWER.message());
        }
    }

    private boolean isDeletableByWriter(NsUser writerOfQuestion) {
        return answers.isEmpty() || answers.stream().allMatch(answer -> answer.isOwner(writerOfQuestion));
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
