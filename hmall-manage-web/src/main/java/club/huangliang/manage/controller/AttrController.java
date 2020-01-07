package club.huangliang.manage.controller;

import club.huangliang.hmall.pojo.PmsBaseAttrInfo;
import club.huangliang.hmall.pojo.PmsBaseAttrValue;
import club.huangliang.hmall.pojo.PmsBaseSaleAttr;
import club.huangliang.hmall.service.AttrService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class AttrController {
    @Reference
    AttrService attrService;
    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id){
        List<PmsBaseAttrInfo> pmsBaseAttrInfos=attrService.attrinfoList(catalog3Id);
        return pmsBaseAttrInfos;
    }
    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){
       String success= attrService.saveAttrInfo(pmsBaseAttrInfo);
        return "success";
    }
    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){
       List<PmsBaseAttrValue> pmsBaseAttrValues=attrService.getAttrValueList(attrId);
       return  pmsBaseAttrValues;
    }
    //查询所有的销售属性
    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> pmsBaseSaleAttrList (){
        List<PmsBaseSaleAttr> pmsBaseSaleAttrList =attrService.baseSaleAttrList();
        return pmsBaseSaleAttrList;
    }

}
