package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {
	private List<Answer> answers = new ArrayList<>();

	public Answers() {
	}

	public void add(Answer answer) {
		this.answers.add(answer);
	}

	public void delete(NsUser loginUser) throws CannotDeleteException {
		for (Answer answer : answers) {
			answer.delete(loginUser);
		}
	}

	public void throwIfNotDeletable(NsUser loginUser) throws CannotDeleteException {
		for (Answer answer : answers) {
			answer.throwIfNotDeletable(loginUser);
		}
	}

	public List<Answer> getAnswers() {
		return new ArrayList<>(answers);
	}
}
