package club.huangliang.hmall.item.controller;

import club.huangliang.hmall.pojo.PmsSkuInfo;
import club.huangliang.hmall.service.SkuService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class ItemController {
    @Reference
    SkuService skuService;

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable String skuId, ModelMap map) {
        PmsSkuInfo pmsSkuInfo = skuService.getSkuById(skuId);
        map.put("skuInfo", pmsSkuInfo);
        return "item";
    }

}
