package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
	private final List<Answer> answers;

	public Answers(List<Answer> answers) {
		this.answers = answers;
	}

	private void checkOwner(NsUser writer) throws CannotDeleteException {
		if (!isOwners(writer)) {
			throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
		}
	}

	public boolean isOwners(NsUser writer) {
		return answers.stream()
				.allMatch(answer -> answer.isOwner(writer));
	}

	public List<DeleteHistory> setDeleteAnswers(NsUser writer) throws CannotDeleteException {
		checkOwner(writer);
		List<DeleteHistory> deleteHistories = new ArrayList<>();
		answers.forEach(answer -> {
			answer.answerDeleted();
			deleteHistories.add(answer.makeDeleteHistory());
		});
		return deleteHistories;
	}

	public Answer getAnswer(int index) {
		return answers.get(index);
	}

}
