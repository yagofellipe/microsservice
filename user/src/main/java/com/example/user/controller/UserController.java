package com.example.user.controller;

import com.example.user.service.UserService;
import com.example.shoppingclient.dtoUser.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    public static List<UserDTO> usuarios = new ArrayList<UserDTO>();


    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }


    @PostMapping
    public UserDTO add(@RequestBody UserDTO userDTO) {
        return service.save(userDTO);
    }


    @GetMapping
    public List<UserDTO> getUsers(){
        return service.getAll();
    }


    @GetMapping("/cpf/{cpf}")
    public UserDTO getByCpf(@PathVariable String cpf, @RequestParam(name = "key", required = true) String key) {
        return service.findByCpf(cpf, key);
    }


    @GetMapping("{id}")
    public UserDTO getById(@PathVariable long id) {
        return service.findById(id);
    }


    @GetMapping("/search")
    public List<UserDTO> search(@RequestParam(name = "nome", required = true) String nome) {
        return service.queryByName(nome);
    }


}
