package com.fh.contriller;

import com.fh.service.ShopBranService;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8080")
public class ShopBrandConTroller {

    @Autowired
    private ShopBranService shopBranService;

    /**
     * 根据商品类别查询品牌信息
     * @param pid
     * @return
     */

    @GetMapping("/{pid}")
    public ResponseServer queryBrandbyTypePid(@PathVariable("pid") String pid){


        return shopBranService.queryBrandbyTypePid(pid);

    }



}
