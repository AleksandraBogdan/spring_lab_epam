package com.epam.dao;

import com.epam.model.ROLE;
import com.epam.model.Task;
import com.epam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class UsersDao implements Dao<User> {

    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return User.builder().id(resultSet.getInt("id")).name(resultSet.getString("name")).
                surname(resultSet.getString("surname")).email(resultSet.getString("email")).
                password(resultSet.getString("password")).subscription(resultSet.getString("subscription")).
                role(ROLE.valueOf(resultSet.getString("role"))).build();

    };

    @Autowired
    public UsersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(User item) {
        jdbcTemplate.update("insert into users values (?, ?, ?, ?, ?, ?, ?)", item.getId(), item.getName(),
                item.getSurname(), item.getEmail(), item.getPassword(), item.getSubscription(), item.getRole());

        return findById(item.getId()).equals(item);
    }

    public User findById(int id) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("select * from users where id = ?", new Object[]{id}, ROW_MAPPER);
        } catch (DataAccessException dataAccessException) {
            dataAccessException.printStackTrace();
        }
        return user;
    }

    public User findByEmail(String email) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("select * from users where email = ?", new Object[]{email}, ROW_MAPPER);
        } catch (DataAccessException dataAccessException) {
            dataAccessException.printStackTrace();
        }
        return user;
    }

    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", ROW_MAPPER);
    }

    @Override
    public void update(int id, User item) {
        jdbcTemplate.update("update users set name = ?2, surname = ?3, email = ?4, password = ?5, subscription = ?6, " +
                        "role = ?7 where id = ?1", item.getId(), item.getName(),
                item.getSurname(), item.getEmail(), item.getPassword(), item.getSubscription(), item.getRole());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("delete from users where id = ?", id);

    }
}
