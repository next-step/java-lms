package nextstep.users.application.model.req;

import org.apache.logging.log4j.util.Strings;

public class ReqChangeUserProfile {
    private final String name;
    private final String email;

    public ReqChangeUserProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getNameDefaultEmpty() {

        if (name == null) {
            return Strings.EMPTY;
        }

        if (name.isEmpty()) {
            return Strings.EMPTY;
        }

        return name;
    }

    public String getEmailDefaultEmpty() {

        if (email == null) {
            return Strings.EMPTY;
        }

        if (email.isEmpty()) {
            return Strings.EMPTY;
        }

        return email;
    }
}
