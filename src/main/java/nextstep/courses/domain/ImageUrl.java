package nextstep.courses.domain;

import java.net.MalformedURLException;
import java.net.URL;

public class ImageUrl {
    private final URL url;

    public ImageUrl(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public String url() {
        return url.toString();
    }

}
