package com.company.microservice.controller;

import com.company.microservice.entity.User;
import com.company.microservice.response.ResponseHandler;
import com.company.microservice.service.ServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class Controller {
    @Autowired
    private ServiceLogin serviceLogin;

    @PostMapping("/")
    public ResponseEntity<Object> saveProduct(@RequestBody User newUser) {
        try {
            User save = serviceLogin.save(newUser);
            if ( save != null){
                return ResponseHandler.generateResponse("Success", HttpStatus.CREATED,save);
            } else{
                return ResponseHandler.generateResponse("Error", HttpStatus.NOT_ACCEPTABLE , "Email o nombre de usuario invalido");
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/access")
    public ResponseEntity<Object> login(@RequestBody User user) {
        try {
            if (serviceLogin.login(user) != null){
                return ResponseHandler.generateResponse("Success", HttpStatus.CREATED,serviceLogin.login(user));
            } else{
                return ResponseHandler.generateResponse("Error", HttpStatus.NOT_ACCEPTABLE , "Email o contrasena invalido");
            }

        } catch (Exception e) {
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try {
            return ResponseHandler.generateResponse("Succes",HttpStatus.OK,serviceLogin.deleteUser(id));
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editProducto(@PathVariable Integer id, @RequestBody User user){
        try {
            if (serviceLogin.updateUser(id,user) == true){
                return ResponseHandler.generateResponse("Succes",HttpStatus.OK,serviceLogin.updateUser(id,user));
            } else {
                return ResponseHandler.generateResponse("Error", HttpStatus.NOT_ACCEPTABLE,"Email o nombre de usuario incorrectos");
            }

        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }
}
