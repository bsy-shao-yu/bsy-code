package com.fh.controller;

import com.fh.service.CarSevice;
import com.fh.shoplongin.LoginAnnotation;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8080",exposedHeaders = "NOLOGIN")
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarSevice carSevice;


    @LoginAnnotation
    @PostMapping
    public ResponseServer addCar(Integer id, HttpServletRequest request){
        String  phone = (String) request.getAttribute("phone");

        return carSevice.addCar(phone,id);
    }



    @LoginAnnotation
    @GetMapping
    public ResponseServer toLookCar(){

        return ResponseServer.success();
    }

}
