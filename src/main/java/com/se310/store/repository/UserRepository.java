package com.se310.store.repository;

import com.se310.store.data.DataManager;
import com.se310.store.model.Store;
import com.se310.store.model.User;
import com.se310.store.model.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * User Repository implements Repository Pattern for user data access layer
 * Uses DataManager for persistent storage
 *
 * This repository is completely database-agnostic - it has no knowledge of SQL,
 * ResultSets, or SQLExceptions. All database-specific logic is encapsulated in DataManager.
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-11-06
 */
public class UserRepository {

    private final DataManager dataManager;

    public UserRepository(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void addUser(User user) throws SQLException {
        dataManager.executeUpdate("INSERT INTO users (email, password, name, role) VALUES (?, ?, ?, ?)",
                user.getEmail(), user.getPassword(), user.getName(), user.getRole());
    }

    public void removeUser(String email) throws SQLException {
        dataManager.executeUpdate("DELETE FROM users WHERE email = ?", email);
    }

    public User findUser(String email) throws SQLException {
        ResultSet result = dataManager.executeQuery("SELECT email, password, name, role FROM users WHERE email = ?",
                email);
        return new User(result.getString(0), result.getString(1),
                result.getString(2), UserRole.valueOf(result.getString(3)));
    }
}