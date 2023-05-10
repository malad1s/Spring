package org.example.DAO;

import org.example.models.Team;

public interface DAOTeams {

     int createTeam(String name, String description);
     void deleteTeam(int id);
     Team getTeam(int id);
     void updateTeam(String name, int id);

     void updateTeamMember(int idMember, int idTeam);
     void updateRoleToMember(int idMember, int idRole);
}
