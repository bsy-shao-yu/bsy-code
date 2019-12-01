package com.fh.controller;

import com.fh.service.CarSevice;
import com.fh.shoplongin.LoginAnnotation;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8080",exposedHeaders = "NOLOGIN")
@RequestMapping("/cars")
public class CarControllerApi {

            @Autowired
            private CarSevice carSevice;


            @LoginAnnotation
            @GetMapping
            public ResponseServer carShow(HttpServletRequest request){
                String  phone = (String) request.getAttribute("phone");
                Map<String , Object> result =  carSevice.carShow(phone);
                return  ResponseServer.success(result) ;
            }

            @LoginAnnotation
            @RequestMapping("addcar1")
            public ResponseServer addcar1(Integer productId,HttpServletRequest request){
                String  phone = (String) request.getAttribute("phone");
              return   carSevice.addcar1(productId,phone);
            }


            @LoginAnnotation
            @RequestMapping("delcar")
            public ResponseServer delCar(Integer productId,HttpServletRequest request){
                String  phone = (String) request.getAttribute("phone");
                return   carSevice.delCar(productId,phone);
            }

            @LoginAnnotation
            @RequestMapping("delProduct")
            public ResponseServer delProduct(Integer productId,HttpServletRequest request){
                String  phone = (String) request.getAttribute("phone");
                return   carSevice.delProduct(productId,phone);
            }

            @LoginAnnotation
            @RequestMapping("updateChexked")
            public ResponseServer updateChexked(String chkvalue,HttpServletRequest request){
                String  phone = (String) request.getAttribute("phone");
                return   carSevice.updateChexked(chkvalue,phone);
            }

}
