package nextstep.courses.infrastructure;

import nextstep.courses.domain.coverImage.CoverImage;
import nextstep.courses.domain.coverImage.CoverImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CoverImage save(CoverImage coverImage) {
        KeyHolder keyHolderForCoverImage = new GeneratedKeyHolder();
        final String sqlForCoverImageSave = "insert into cover_image (name, capacity, width, height, type) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlForCoverImageSave, new String[]{"id"});
            ps.setString(1, coverImage.getName());
            ps.setDouble(2, coverImage.getCapacity());
            ps.setDouble(3, coverImage.getWidth());
            ps.setDouble(4, coverImage.getHeight());
            ps.setString(5, coverImage.getImageType().name());
            return ps;
        }, keyHolderForCoverImage);
        coverImage.updateAsSavedCoverImage(keyHolderForCoverImage.getKey().longValue());

        return coverImage;
    }
}
