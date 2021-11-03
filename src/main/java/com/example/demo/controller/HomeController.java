package com.example.demo.controller;

import com.example.demo.pojo.Alien;
import com.example.demo.repository.AlienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private AlienRepository alienRepository;



    @PostMapping("/add")
    @ResponseBody
    public void add(Alien alien) {
        if (alienRepository.existsById(alien.getAid()))
        {

            System.out.println("Can't add. Alien already exists");
        }
        else{
            alienRepository.save(alien);
            System.out.println("Alien added successfully");
        }


    }

    @GetMapping("/getaliens")
    @ResponseBody
    public List<Alien> getAliens(HttpServletRequest request, HttpServletResponse response) {

        List<Alien> aliens= (List<Alien>) alienRepository.findAll();
        return aliens;

    }

    @GetMapping("/getalien")
    @ResponseBody
    public Alien getAlien(@RequestParam("aid") int id) {

        Optional<Alien> alien= alienRepository.findById(id);
        return alien.orElse(null);



    }

    @GetMapping("/getaliensbyaname")
    @ResponseBody
    public List<Alien> getAliensByaName(@RequestParam("aname") String name) {
        List<Alien> alien= alienRepository.findByEnameSortByAid(name);
        return alien;

    }

    @PostMapping("/updatealien")
    @ResponseBody
    public void updateAlien(Alien alien) {
        if (alienRepository.existsById(alien.getAid()))
        {
            alienRepository.save(alien);

            System.out.println("Alien updated successfully");
        }
        else{
            System.out.println("Alien doesn't exist");
        }



    }


    @DeleteMapping("/deletealien")
    @ResponseBody
    public void deleteAlien(@RequestParam("aid") int id) {


        if(alienRepository.existsById(id)){
            alienRepository.deleteById(id);
            System.out.println("Alien deleted successfully");
        }
        else
            System.out.println("Record doesn't exist");

    }



}