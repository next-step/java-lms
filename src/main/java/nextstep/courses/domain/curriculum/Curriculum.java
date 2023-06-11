package nextstep.courses.domain.curriculum;

import java.util.Objects;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.batch.Batch;

public class Curriculum {

  private Long id;

  private Batch batch;

  private Session session;

  public Curriculum(Batch batch, Session session) {
    this(null, batch, session);
  }

  public Curriculum(Long id, Batch batch, Session session) {
    this.id = id;
    this.batch = batch;
    this.session = session;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Curriculum that = (Curriculum) o;
    return Objects.equals(batch, that.batch) && Objects
        .equals(session, that.session);
  }

  @Override
  public int hashCode() {
    return Objects.hash(batch, session);
  }
}
