package club.huangliang.hmall.service;

import club.huangliang.hmall.pojo.PmsSkuInfo;

public interface SkuService {
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getSkuById(String skuId);

}
