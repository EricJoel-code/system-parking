package com.example.system_parking.controllers;

import com.example.system_parking.models.Client;
import com.example.system_parking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients/new")
    public String showCreateForm(Model model) {
        model.addAttribute("client", new Client());
        return "create_client";
    }

    @PostMapping("/clients")
    public String createClient(@ModelAttribute Client client) {
        clientRepository.save(client);
        return "redirect:/clients/new";
    }
}

