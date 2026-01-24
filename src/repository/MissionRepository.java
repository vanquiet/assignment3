package repository;

import db.DatabaseConnection;
import exceptions.DatabaseOperationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MissionRepository {

    public int create(String name, double distanceKm,
                      double requiredFuel, int shipId, int pilotId) {

        String sql = "INSERT INTO missions(name, distance_km, required_fuel, ship_id, pilot_id) " +
                "VALUES(?,?,?,?,?) RETURNING id";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setDouble(2, distanceKm);
            ps.setDouble(3, requiredFuel);
            ps.setInt(4, shipId);
            ps.setInt(5, pilotId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
                return 0;
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("create mission failed", e);
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM missions WHERE id=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DatabaseOperationException("delete mission failed", e);
        }
    }

    public List<String> getAllSimple() {
        String sql = "SELECT id, name FROM missions ORDER BY id";
        List<String> list = new ArrayList<>();

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add("MISSION#" + rs.getInt("id") + " " + rs.getString("name"));
            }
            return list;

        } catch (SQLException e) {
            throw new DatabaseOperationException("get all missions failed", e);
        }
    }
}
