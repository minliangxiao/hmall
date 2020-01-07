package club.huangliang.hmall.service;

import club.huangliang.hmall.pojo.PmsBaseAttrInfo;
import club.huangliang.hmall.pojo.PmsBaseAttrValue;
import club.huangliang.hmall.pojo.PmsBaseSaleAttr;

import java.util.List;

public interface AttrService {
    List<PmsBaseAttrInfo> attrinfoList(String catalog3Id);


    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();

}
