package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class BaseEntity {
    private Long id;
    private NsUser writer;
    private String contents;

    public BaseEntity(Long id, NsUser writer, String contents) {
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        this.id = id;
        this.writer = writer;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public boolean isWrittenBy(NsUser user) {
        return writer.equals(user);
    }

    public DeleteHistory toDeleteHistory(ContentType contentType) {
        return new DeleteHistory(contentType, id, writer);
    }

    @Override
    public String toString() {
        return "id=" +  id + "writer=" + writer + ", contents=" + contents;
    }
}
