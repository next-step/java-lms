package nextstep.sessions.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UrlPattern {

    private static final String URL_PATTERN = "^https?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
    private static final Pattern PATTERN = Pattern.compile(URL_PATTERN);

    private UrlPattern() {
        throw new IllegalCallerException("잘못된 객체생성입니다.");
    }

    public static boolean isValid(String url) {
        Matcher matcher = PATTERN.matcher(url);
        return matcher.matches();
    }

}
