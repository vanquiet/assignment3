package repository;

import db.DatabaseConnection;
import models.Ship;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShipRepository implements CrudRepository<Ship> {

    private final Connection conn;

    public ShipRepository() {
        this.conn = DatabaseConnection.getConnection();
    }

    @Override
    public Ship create(Ship ship) {
        String sql = "INSERT INTO ships(name, fuel_level, status) VALUES (?, ?, ?) RETURNING id";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ship.getName());
            ps.setDouble(2, ship.getFuelLevel());   // 🔥 double
            ps.setString(3, ship.getStatus());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ship.setId(rs.getInt("id"));
                return ship;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("create ship failed", e);
        }
    }

    @Override
    public Ship getById(int id) {
        String sql = "SELECT * FROM ships WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return map(rs);
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("get ship failed", e);
        }
    }

    @Override
    public List<Ship> getAll() {
        List<Ship> list = new ArrayList<>();
        String sql = "SELECT * FROM ships";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("get ships failed", e);
        }
        return list;
    }

    @Override
    public boolean update(int id, Ship ship) {
        String sql = "UPDATE ships SET name=?, fuel_level=?, status=? WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ship.getName());
            ps.setDouble(2, ship.getFuelLevel());   // 🔥 double
            ps.setString(3, ship.getStatus());
            ps.setInt(4, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("update ship failed", e);
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM ships WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("delete ship failed", e);
        }
    }

    private Ship map(ResultSet rs) throws SQLException {
        Ship s = new Ship();
        s.setId(rs.getInt("id"));
        s.setName(rs.getString("name"));
        s.setFuelLevel(rs.getDouble("fuel_level")); // 🔥 double
        s.setStatus(rs.getString("status"));
        return s;
    }
}
