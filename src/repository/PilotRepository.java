package repository;

import db.DatabaseConnection;
import exceptions.DatabaseOperationException;
import models.Pilot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PilotRepository {

    public Pilot create(Pilot p) {
        String sql = "INSERT INTO pilots(name, license_code, active) VALUES(?,?,?) RETURNING id";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setString(2, p.getLicenseCode());
            ps.setBoolean(3, p.isActive());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) p.setId(rs.getInt(1)); // ✅ только так
            }
            return p;

        } catch (SQLException e) {
            throw new DatabaseOperationException("create pilot failed", e);
        }
    }

    public Pilot getById(int id) {
        String sql = "SELECT id, name, license_code, active FROM pilots WHERE id=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return new Pilot(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("license_code"),
                        rs.getBoolean("active")
                );
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("get pilot failed", e);
        }
    }

    public List<Pilot> getAll() {
        String sql = "SELECT id, name, license_code, active FROM pilots ORDER BY id";
        List<Pilot> list = new ArrayList<>();

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Pilot(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("license_code"),
                        rs.getBoolean("active")
                ));
            }
            return list;

        } catch (SQLException e) {
            throw new DatabaseOperationException("get all pilots failed", e);
        }
    }

    public boolean update(int id, Pilot p) {
        String sql = "UPDATE pilots SET name=?, license_code=?, active=? WHERE id=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setString(2, p.getLicenseCode());
            ps.setBoolean(3, p.isActive());
            ps.setInt(4, id);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DatabaseOperationException("update pilot failed", e);
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM pilots WHERE id=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DatabaseOperationException("delete pilot failed", e);
        }
    }

    public boolean existsByLicense(String licenseCode) {
        String sql = "SELECT 1 FROM pilots WHERE license_code=? LIMIT 1";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, licenseCode);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("existsByLicense failed", e);
        }
    }
}

