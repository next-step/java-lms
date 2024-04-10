package nextstep.common.infrastructure;

import nextstep.common.domain.DeleteHistoryRepository;
import nextstep.common.dto.DeleteHistoryDto;
import nextstep.utils.DbTimestampUtils;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("deleteHistoryRepository")
public class JdbcDeleteHistoryRepository implements DeleteHistoryRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcDeleteHistoryRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(List<DeleteHistoryDto> deleteHistories) {
        deleteHistories.forEach(this::save);
    }

    public void save(DeleteHistoryDto deleteHistoryDto) {
        String sql = "insert into deleteHistory (content_id, content_type, created_date, deleted_by_id) values(?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                deleteHistoryDto.getContentId(),
                deleteHistoryDto.getContentType(),
                DbTimestampUtils.toTimestamp(deleteHistoryDto.getCreatedAt()),
                deleteHistoryDto.getDeletedById()
        );
    }
}
