package org.example.DAO;

import org.example.DBConnection.PostgresConnectionPool;
import org.example.models.Team;

import java.sql.*;
import java.time.OffsetDateTime;

public class DAOTeamsImplPostgres implements DAOTeams{

    private final String updateTeam="Update public.\"API_team\" set name=? WHERE id=?";
    private final String createTeam="INSERT INTO public.\"API_team\" (name,description,created_at) VALUES (?,?,?) RETURNING id";
    private final String deleteTeam="DELETE FROM public.\"API_team\" WHERE id=?";
    private final String getTeam="Select * FROM public.\"API_team\" WHERE id=?";
    private final String updateUserRole="Update public.\"user_user\" set role=? WHERE id=?";
    private final String updateUserTeam="Update public.\"user_user\" set team_id=? WHERE id=?";
    @Override
    public int createTeam(String name, String description) {
        Connection con= null;
        PreparedStatement prst=null;
        int generatedKey = 0;
        try {
            con = PostgresConnectionPool.getConnection();
            prst=con.prepareStatement(createTeam, Statement.RETURN_GENERATED_KEYS);
            prst.setString(1,name);
            prst.setString(2,description);
            prst.setObject(3,OffsetDateTime.now());
            prst.executeUpdate();
            ResultSet rs = prst.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            System.out.println(generatedKey);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                closePrepareState(prst);
                closeCon(con);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return generatedKey;
    }

    @Override
    public void deleteTeam(int id) {
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = PostgresConnectionPool.getConnection();
            prst=con.prepareStatement(deleteTeam);
            prst.setInt(1,id);
            prst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                closePrepareState(prst);
                closeCon(con);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public Team getTeam(int id) {
        Team res= null;
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = PostgresConnectionPool.getConnection();
            prst=con.prepareStatement(getTeam);
            prst.setInt(1,id);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                res= new Team(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
        } catch (SQLException e) {
        } finally {
            try {
               closePrepareState(prst);
               closeCon(con);
            } catch (SQLException e) {
            }
        }
        return res;
    }

    @Override
    public void updateTeam(String name, int id) {
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = PostgresConnectionPool.getConnection();
            prst=con.prepareStatement(updateTeam);
            prst.setString(1,name);
            prst.setInt(2,id);
            prst.executeUpdate();
        } catch (SQLException e) {

        } finally {
            try {
                closePrepareState(prst);
                closeCon(con);
            } catch (SQLException e) {

            }
        }
    }

    @Override
    public void updateTeamMember(int idMember, int idTeam) {
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = PostgresConnectionPool.getConnection();
            prst=con.prepareStatement(updateUserTeam);
            prst.setInt(1,idTeam);
            prst.setInt(2, idMember);
            prst.executeUpdate();

        } catch (SQLException e) {

        } finally {
            try {
                closePrepareState(prst);
                closeCon(con);
            } catch (SQLException e) {

            }
        }
    }
    @Override
    public void updateRoleToMember(int idMember, int idRole) {
        Connection con= null;
        PreparedStatement prst=null;
        try {
            con = PostgresConnectionPool.getConnection();
            prst=con.prepareStatement(updateUserRole);
            prst.setInt(1, idRole);
            prst.setInt(2, idMember);
            prst.executeUpdate();
        } catch (SQLException e) {

        } finally {
            try {
                closePrepareState(prst);
                closeCon(con);
            } catch (SQLException e) {

            }
        }
    }


    public void closeCon(Connection con) throws SQLException {
        if(con!=null){
            con.close();
        }
    }
    public void closePrepareState(PreparedStatement prst) throws SQLException {
        if(prst!=null){
            prst.close();
        }
    }


}
