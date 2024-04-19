package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
	private Long id;

	private String title;

	private String contents;

	private NsUser writer;

	private List<Answer> answers = new ArrayList<>();

	private boolean deleted = false;

	private LocalDateTime createdDate = LocalDateTime.now();

	private LocalDateTime updatedDate;

	public Question() {
	}

	public Question(NsUser writer, String title, String contents) {
		this(0L, writer, title, contents);
	}

	public Question(Long id, NsUser writer, String title, String contents) {
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}

	public Long getId() {
		return id;
	}

	public NsUser getWriter() {
		return writer;
	}

	public void addAnswer(Answer answer) {
		answer.toQuestion(this);
		answers.add(answer);
	}

	public boolean isOwner(NsUser loginUser) {
		return writer.equals(loginUser);
	}

	public Question setDeleted(boolean deleted) {
		this.deleted = deleted;
		return this;
	}

	public Question setDeleted(NsUser register, List<DeleteHistory> deleteHistories) throws CannotDeleteException {
		if (!this.isOwner(register)) {
			throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
		}

		deleteQuestion(deleteHistories);

		if (!answers.isEmpty()) {
			deletedAnswers(deleteHistories);
		}

		return this;
	}

	private void deleteQuestion(List<DeleteHistory> deleteHistories) {
		this.deleted = true;
		this.addDeleteHistory(deleteHistories);
	}

	public Question setDeleted(NsUser register) throws CannotDeleteException {
		if (!this.isOwner(register)) {
			throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
		}
		this.deleted = true;
		return this;
	}

	private void deletedAnswers(List<DeleteHistory> deleteHistories) throws CannotDeleteException {
		Answers answersCollection = new Answers(answers);
		answersCollection.setDeleteAnswers(writer, deleteHistories);
	}

	public void addDeleteHistory(List<DeleteHistory> deleteHistories) {
		deleteHistories.add(new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now()));
	}

	public boolean isDeleted() {
		return deleted;
	}

	@Override
	public String toString() {
		return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
	}

}
