package nextstep.courses.domain;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class SessionCoverUrl {

  private static final String ILLEGAL_URL_MESSAGE = "URL 형식이어야 합니다. 현재 URL : ";
  private final String sessionCoverUrl;

  public SessionCoverUrl(String sessionCoverUrl) {
    validateUrl(sessionCoverUrl);

    this.sessionCoverUrl = sessionCoverUrl;
  }

  public SessionCoverUrl changeCoverImg(String coverUrl) {
    return new SessionCoverUrl(coverUrl);
  }

  private void validateUrl(String url) {
    if (!isUrlFormat(url)) {
      throw new IllegalArgumentException(ILLEGAL_URL_MESSAGE + url);
    }
  }

  private boolean isUrlFormat(String url) {
    try {
      new URL(url).toURI();
      return true;
    } catch (MalformedURLException | URISyntaxException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SessionCoverUrl that = (SessionCoverUrl) o;

    return Objects.equals(sessionCoverUrl, that.sessionCoverUrl);
  }

  @Override
  public int hashCode() {
    return sessionCoverUrl != null ? sessionCoverUrl.hashCode() : 0;
  }
}
