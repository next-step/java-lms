package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.Objects;

public class ContentBody {
    private final NsUser writer;
    private final String contents;

    public ContentBody(NsUser writer, String contents) {
        this.writer = writer;
        this.contents = contents;
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public NsUser getWriter() {
        return writer;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContentBody that = (ContentBody) o;
        return Objects.equals(writer, that.writer) && Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(writer, contents);
    }
}
