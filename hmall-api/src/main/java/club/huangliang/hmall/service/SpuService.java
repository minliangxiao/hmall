package club.huangliang.hmall.service;

import club.huangliang.hmall.pojo.PmsProductImage;
import club.huangliang.hmall.pojo.PmsProductInfo;
import club.huangliang.hmall.pojo.PmsProductSaleAttr;

import java.util.List;

public interface SpuService {


    List<PmsProductInfo> spuList(String cataLog3Id);

    void saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);
}
