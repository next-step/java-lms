package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {
	private List<Answer> answers = new ArrayList<>();

	private final Question question;

	public Answers(Question question) {
		this.question = question;
	}

	public void add(Answer answer) {
		answer.toQuestion(question);
		answers.add(answer);
	}

	public void delete() throws CannotDeleteException {
		try {
			validateDeletable(question.getWriter());

			deleteAnswers();
		} catch(CannotDeleteException e) {
			throw e;
		}
	}

	private void deleteAnswers() throws CannotDeleteException {
		for (Answer answer : answers) {
			answer.delete();
		}
	}

	public boolean validateDeletable(NsUser writer) throws CannotDeleteException {
		if (isEmpty()) {
			return true;
		}

		if (!isAllSameWriter(writer)) {
			throw new CannotDeleteException("질문자와 답변자가 다르면 삭제할 수 없습니다.");
		}

		return true;
	}

	public boolean isAllDeleted() {
		return answers.stream()
			.allMatch(Answer::isDeleted);
	}

	private boolean isAllSameWriter(NsUser user) {
		return answers.stream()
			.allMatch(answer -> answer.isOwner(user));
	}

	private boolean isEmpty() {
		return answers.isEmpty();
	}
}
