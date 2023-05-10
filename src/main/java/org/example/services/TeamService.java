package org.example.services;

import org.example.DAO.DAOTeams;
import org.example.DAO.DAOTeamsImplPostgres;
import org.example.DBConnection.PostgresConnectionPool;
import org.example.models.Team;

import java.sql.Connection;
import java.sql.SQLException;

public class TeamService {


    public void createTeamByUser(Team team,int idMember, int role) throws SQLException {
        DAOTeams db= new DAOTeamsImplPostgres();
        int idTeam=db.createTeam(team.getName(),team.getDescription());
        db.updateTeamMember(idMember,idTeam);
        db.updateRoleToMember(idMember,role);
    }
    public void addNewMemberInTeam(int idMember,int idTeam,int idRole){

    }
    public void updateMemberRole(int idMember,int idRole){

    }
    public void updateTeam(int idMember,int idRole){

    }
    public void kickMemberFromTeam(int idMember,int idRole){


    }


}
