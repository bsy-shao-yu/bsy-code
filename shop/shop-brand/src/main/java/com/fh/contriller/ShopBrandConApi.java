package com.fh.contriller;

import com.fh.service.ShopBranService;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brandId")
public class ShopBrandConApi {


            @Autowired
            private ShopBranService shopBranService;


            @GetMapping("/{id}")
            public ResponseServer getBrandIdCar(@PathVariable("id") Integer id){

                return shopBranService.getBrandIdCar(id);

            }


}
