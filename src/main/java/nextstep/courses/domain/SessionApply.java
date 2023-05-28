package nextstep.courses.domain;

import nextstep.courses.domain.base.CreatedDate;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class SessionApply extends CreatedDate {

    private Long id;
    private Session session;
    private NsUser user;

    public SessionApply(Long id, Session session, NsUser user) {
        this.id = id;
        this.session = session;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionApply)) return false;
        SessionApply that = (SessionApply) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
