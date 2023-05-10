package org.example.controllers;

import org.example.DAO.DAOTeamsImplPostgres;
import org.example.DBConnection.PostgresConnectionPool;
import org.example.models.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String home(Model model){
         Team team= new DAOTeamsImplPostgres().getTeam(15);
        return team.toString();
    }
}
