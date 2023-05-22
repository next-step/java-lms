package nextstep.users.domain;

public class NsUsers {
    public static NsUser createNsUser(Long id) {
        return new NsUser(id, "javajigi", "password", "name", "javajigi@slipp.net");
    }
}
