package repository;

import db.DatabaseConnection;
import exceptions.DatabaseOperationException;
import models.Ship;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShipRepository {

    public Ship create(Ship s) {
        String sql = "INSERT INTO ships(name, fuel_level, status) VALUES(?,?,?) RETURNING id";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setDouble(2, s.getFuelLevel());
            ps.setString(3, s.getStatus());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) s.setId(rs.getInt(1));
            }
            return s;

        } catch (SQLException e) {
            throw new DatabaseOperationException("create ship failed", e);
        }
    }

    public Ship getById(int id) {
        String sql = "SELECT id, name, fuel_level, status FROM ships WHERE id=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return new Ship(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("fuel_level"),
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("get ship failed", e);
        }
    }

    public List<Ship> getAll() {
        String sql = "SELECT id, name, fuel_level, status FROM ships ORDER BY id";
        List<Ship> list = new ArrayList<>();

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Ship(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("fuel_level"),
                        rs.getString("status")
                ));
            }
            return list;

        } catch (SQLException e) {
            throw new DatabaseOperationException("get all ships failed", e);
        }
    }

    public boolean update(int id, Ship s) {
        String sql = "UPDATE ships SET name=?, fuel_level=?, status=? WHERE id=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setDouble(2, s.getFuelLevel());
            ps.setString(3, s.getStatus());
            ps.setInt(4, id);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DatabaseOperationException("update ship failed", e);
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM ships WHERE id=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DatabaseOperationException("delete ship failed", e);
        }
    }
}

