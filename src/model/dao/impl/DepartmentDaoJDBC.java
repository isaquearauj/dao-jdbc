package model.dao.impl;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private final Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO department (Name) VALUES (?)";
        try {
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, department.getName());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    department.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pstmt);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement pstmt = null;
        String sql = "UPDATE department SET Name = ? WHERE Id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, department.getName());
            pstmt.setInt(2, department.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pstmt);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM department WHERE Id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(pstmt);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM department WHERE Id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return instantiateDepartment(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pstmt);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM department";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            List<Department> departments = new ArrayList<>();
            while (rs.next()) {
                departments.add(instantiateDepartment(rs));
            }
            return departments;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(stmt);
            DB.closeResultSet(rs);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}
