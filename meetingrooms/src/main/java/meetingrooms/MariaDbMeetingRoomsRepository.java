package meetingrooms;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class MariaDbMeetingRoomsRepository implements MeetingRoomsRepository {

    private JdbcTemplate jdbcTemplate;

    public MariaDbMeetingRoomsRepository() {
        try {
            MariaDbDataSource dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/employees?useUnicode=true");
            dataSource.setUser("employees");
            dataSource.setPassword("employees");

            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            flyway.migrate();

            jdbcTemplate = new JdbcTemplate(dataSource);

        } catch (SQLException sql) {
            throw new IllegalStateException("Cannot create datasource" + sql);
        }
    }

    @Override
    public void save(String name, int width, int length) {
        jdbcTemplate.update("INSERT INTO meetingrooms(name, width, length) VALUES (?,?,?)", name, width, length);
    }

    @Override
    public List<String> meetingRoomsByABC() {
        return jdbcTemplate.query("SELECT name FROM meetingrooms ORDER BY name",
                (rs, i) -> rs.getString("name"));
    }

    @Override
    public List<String> meetingRoomsReversed() {
        return jdbcTemplate.query("SELECT name FROM meetingrooms ORDER BY name DESC",
                (rs, i) -> rs.getString("name"));
    }

    @Override
    public List<String> meetingRoomsEverySecond() {
        return jdbcTemplate.query("SET @inc = 1; SELECT * FROM (SELECT name,@inc:=1-@inc autoinc FROM meetingrooms ORDER BY name) sorted WHERE autoinc = 1",
                (rs, i) -> rs.getString("name"));
    }

    @Override
    public List<MeetingRoom> meetingRoomsByArea() {
        return jdbcTemplate.query("SELECT * FROM meetingrooms ORDER BY width*length DESC",
                (rs, i) -> new MeetingRoom(rs.getLong("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("length")));
    }

    @Override
    public MeetingRoom exactSearch(String name) {
        return jdbcTemplate.query("SELECT * FROM meetingrooms WHERE name = ?", new Object[]{name},
                (rs, i) -> new MeetingRoom(rs.getLong("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("length"))).get(0);
    }

    @Override
    public List<MeetingRoom> partSearch(String part) {
        return jdbcTemplate.query("SELECT * FROM meetingrooms WHERE name LIKE ? ORDER BY name", new Object[]{"%" + part + "%"},
                (rs, i) -> new MeetingRoom(rs.getLong("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("length")));
    }

    @Override
    public List<MeetingRoom> searchByArea(int area) {
        return jdbcTemplate.query("SELECT * FROM meetingrooms WHERE width*length > ? ORDER BY name", new Object[]{area},
                (rs, i) -> new MeetingRoom(rs.getLong("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("length")));
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM meetingrooms");
    }
}