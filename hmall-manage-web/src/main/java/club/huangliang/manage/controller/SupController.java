package club.huangliang.manage.controller;

import club.huangliang.hmall.pojo.PmsProductImage;
import club.huangliang.hmall.pojo.PmsProductInfo;
import club.huangliang.hmall.pojo.PmsProductSaleAttr;
import club.huangliang.hmall.service.SpuService;
import club.huangliang.manage.util.PmsUploadUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin
public class SupController {
    @Reference
    SpuService spuService;
    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String cataLog3Id){
        List<PmsProductInfo> pmsProductInfos= spuService.spuList(cataLog3Id);
        return pmsProductInfos;
    }
    //保存spu
    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String  saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        spuService.saveSpuInfo(pmsProductInfo);

        return "success";
    }
                //图片上传
        @RequestMapping("fileUpload")
        @ResponseBody
        public String  fileUpload(@RequestParam("file")MultipartFile multipartFile){
            //将图片或者视屏上传到分布式的文件存储系统
            String  imgUrl= PmsUploadUtil.uploadImage(multipartFile);

        //将文件的地址返回到页面
        System.out.println(imgUrl);
        return imgUrl;
    }


    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId){

        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrs;
    }


    @RequestMapping("spuImageList")
    @ResponseBody
    public List<PmsProductImage> spuImageList(String spuId){

        List<PmsProductImage> pmsProductImages = spuService.spuImageList(spuId);
        return pmsProductImages;
    }

}
