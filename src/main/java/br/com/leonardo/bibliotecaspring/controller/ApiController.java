package br.com.leonardo.bibliotecaspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiController {

    @RequestMapping("/api")
    public String inicial(){
        return "cadastro.html";
    }
}
