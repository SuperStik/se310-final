package com.se310.store.repository;

import com.se310.store.data.DataManager;
import com.se310.store.model.Store;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Store Repository implements Repository Pattern for store data access layer
 * Uses DataManager for persistent storage
 *
 * This repository is completely database-agnostic - it has no knowledge of SQL,
 * ResultSets, or SQLExceptions. All database-specific logic is encapsulated in DataManager.
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-11-06
 */
public class StoreRepository {

    private final DataManager dataManager;

    public StoreRepository(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void addStore(Store store) throws SQLException {
        dataManager.executeUpdate("INSERT INTO stores (id, address, description) VALUES (?, ?, ?)", store.getId(),
                store.getAddress(), store.getDescription());
    }

    public void removeStore(String id) throws SQLException {
        dataManager.executeUpdate("DELETE FROM stores WHERE id = ?", id);
    }

    public Store findStore(String id) throws SQLException {
        ResultSet result = dataManager.executeQuery("SELECT id, address, description FROM stores WHERE id = ?",
                id);
        return new Store(result.getString(0), result.getString(1),
                result.getString(2));
    }
}