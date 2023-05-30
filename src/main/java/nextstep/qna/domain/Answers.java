package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {
	private final List<Answer> answers = new ArrayList<>();

	private final Question question;

	public Answers(Question question) {
		this.question = question;
	}

	public List<DeleteHistory> deleteHistories() {
		return answers.stream()
			.map(Answer::deleteHistory)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.collect(Collectors.toList());
	}

	public void add(Answer answer) {
		answer.toQuestion(question);
		answers.add(answer);
	}

	public void deleteAll() throws CannotDeleteException {
		validateDeletable(question.getWriter());

		deleteAnswers();
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
			throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
		}

		return true;
	}

	public boolean isAllDeleted() {
		return answers.stream()
			.allMatch(Answer::isDeleted);
	}

	private boolean isAllSameWriter(NsUser writer) {
		return answers.stream()
			.allMatch(answer -> answer.isOwner(writer));
	}

	private boolean isEmpty() {
		return answers.isEmpty();
	}
}
