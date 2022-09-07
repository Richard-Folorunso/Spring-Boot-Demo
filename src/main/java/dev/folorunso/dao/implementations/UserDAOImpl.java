package dev.folorunso.dao.implementations;

import dev.folorunso.dao.UserDAO;
import dev.folorunso.models.User;
import dev.folorunso.models.maps.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from users";
        try {
            return getJdbcTemplate() != null ? getJdbcTemplate().query(sql, new UserMapper()) : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User findById(int id) {
        String sql = "select * from users where user_id = ?";
        try {
            return getJdbcTemplate() != null ? getJdbcTemplate().queryForObject(sql, new UserMapper(), id) : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User save(User user) {
        String sql = "insert into users values (default, ?)";
        if (getJdbcTemplate() != null) {
            getJdbcTemplate().update(sql, user.getUser_data());
        }
        return user;
    }

    @Override
    public User update(User user) {
        if (findById(user.getId()) != null) {
            String sql = "update users set user_data = ? where user_id = ?";
            if (getJdbcTemplate() != null) {
                getJdbcTemplate().update(sql, user.getUser_data(), user.getId());
            }
            return user;
        }
        return null;
    }

    @Override
    public User delete(int id) {
        User user = findById(id);
        if (user != null) {
            String sql = "delete from users where user_id = ?";
            if (getJdbcTemplate() != null) {
                getJdbcTemplate().update(sql, id);
            }
            return user;
        }
        return null;
    }
}
