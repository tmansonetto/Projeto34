package model.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void insert(Department department) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
                    "INSERT INTO department "
                            + "(Name) "
                            + "VALUES "
                            + "(?)",
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, department.getName());


            int rowsaffected = stmt.executeUpdate();
            if (rowsaffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    department.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Erro ao inserir departamento");
            }
        }
        catch (SQLException e) {
            throw new DbException("Erro ao inserir departamento" + e.getMessage());
        }
        finally {
            DB.closeStatement(stmt);

        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
                    "UPDATE department\n" +
                            "SET Name = ? " +
                            "WHERE Id = ?");

            stmt.setString(1, department.getName());
            stmt.setInt(2, department.getId());

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException("Erro ao atualizar departamento" + e.getMessage());
        }
        finally {
            DB.closeStatement(stmt);

        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("DELETE FROM department WHERE id = ?");
            stmt.setInt(1,id);
            int rowsaffected =   stmt.executeUpdate();

            if (rowsaffected == 0) {
                throw new DbException("Erro ao deletar departamento, id não existente");
            }
        }
        catch (SQLException e) {
            throw new DbException("Erro ao deletar departamento" + e.getMessage());
        }
        finally {
            DB.closeStatement(stmt);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT department.* " +
                            "FROM department " +
                            "WHERE department.Id = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Department department = instantiateDepartment(resultSet);
                return department;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException((e.getMessage()));
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT department.* " +
                            "FROM department " +
                            "ORDER BY Name");

            resultSet = preparedStatement.executeQuery();
            List<Department> departments = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (resultSet.next()) {
                Department dep = map.get(resultSet.getInt("id"));
                if (dep == null) {
                    dep = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("id"), dep);
                }
                departments.add(dep);
            }
            return departments;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(preparedStatement);
        }
    }
    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department dep = new Department();
        dep.setId(resultSet.getInt("id"));
        dep.setName(resultSet.getString("Name"));
        return dep;
    }
}
