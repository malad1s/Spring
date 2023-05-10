package org.example.controllers;

import org.example.DAO.DAOTeamsImplPostgres;
import org.example.DBConnection.PostgresConnectionPool;
import org.example.models.Team;
import org.example.services.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("teams")
public class MainController {


    @GetMapping("/{id}")
    public Map<String , String> getTeam(@PathVariable int id){
        Team team= new DAOTeamsImplPostgres().getTeam(id);
        Map<String , String> result =new HashMap<>();
        result.put("idTeam",team.getId()+"");
        result.put("nameTeam",team.getName()+"");
        result.put("descriptionTeam",team.getDescription()+"");
        result.put("dateTeam",team.getDate_create()+"");
        return result;
    }


    @PostMapping("/createTeam")
    public Map<String , String> createTeam(@RequestBody Map<String,String> info){
        //get info about team
        Team team =new Team(
                0,
                info.get("nameTeam"),
                info.get("descriptionTeam"),
                info.get("dateTeam")
        );
        int id=new TeamService().createTeamByUser(team,Integer.parseInt(info.get("idMember")),1);
        return new HashMap<String, String>(){
            {
                put("idTeam",id+"");
            }
        };
    }

}
