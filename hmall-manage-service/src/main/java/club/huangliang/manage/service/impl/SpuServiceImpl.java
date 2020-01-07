package club.huangliang.manage.service.impl;

import club.huangliang.hmall.pojo.PmsProductImage;
import club.huangliang.hmall.pojo.PmsProductInfo;
import club.huangliang.hmall.pojo.PmsProductSaleAttr;
import club.huangliang.hmall.pojo.PmsProductSaleAttrValue;
import club.huangliang.hmall.service.SpuService;
import club.huangliang.manage.mapper.PmsProductImageMapper;
import club.huangliang.manage.mapper.PmsProductInfoMapper;
import club.huangliang.manage.mapper.PmsProductSaleAttrMapper;
import club.huangliang.manage.mapper.PmsProductSaleAttrValueMapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;

    @Autowired
    PmsProductImageMapper pmsProductImageMapper;

    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;

    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    @Override
    public List<PmsProductInfo> spuList(String cataLog3Id) {
        PmsProductInfo pmsProductInfo=new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(cataLog3Id);
        List<PmsProductInfo> pmsProductInfos=pmsProductInfoMapper.select(pmsProductInfo);
        return pmsProductInfos;
    }

    @Override
    public void saveSpuInfo(PmsProductInfo pmsProductInfo) {
        //保存商品信息
        pmsProductInfoMapper.insertSelective(pmsProductInfo);
        //生成商品主键
        String productId=pmsProductInfo.getId();
        //保存商品图片
        List<PmsProductImage> spuImageList=pmsProductInfo.getSpuImageList();
        for (PmsProductImage pmsProductImage : spuImageList) {
            pmsProductImage.setProductId(productId);
            pmsProductImageMapper.insertSelective(pmsProductImage);
        }
        //保存销售属性信息
        List<PmsProductSaleAttr> spuSaleAttrList=pmsProductInfo.getSpuSaleAttrList();
        for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList) {
            pmsProductSaleAttr.setProductId(productId);
            pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);
            //保存销售属性值
            List<PmsProductSaleAttrValue> spuSaleAttrValueList=pmsProductSaleAttr.getSpuSaleAttrValueList();
            for (PmsProductSaleAttrValue pmsProductSaleAttrValue : spuSaleAttrValueList) {
                    pmsProductSaleAttrValue.setProductId(productId);
                    pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);

            }
        }

    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        PmsProductSaleAttr pmsProductSaleAttr =new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrs= pmsProductSaleAttrMapper.select(pmsProductSaleAttr);

        for (PmsProductSaleAttr productSaleAttr : pmsProductSaleAttrs) {
            PmsProductSaleAttrValue pmsProductSaleAttrValue=new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(spuId);
            pmsProductSaleAttrValue.setSaleAttrId(productSaleAttr.getSaleAttrId());// 销售属性id用的是系统的字典表中id，不是销售属性表的主键
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueMapper.select(pmsProductSaleAttrValue);
            productSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);

        }
        return pmsProductSaleAttrs;
    }
//查询出spu图片集合
    @Override
    public List<PmsProductImage> spuImageList(String spuId) {

        PmsProductImage pmsProductImage=new PmsProductImage();
        pmsProductImage.setProductId(spuId);
        List<PmsProductImage> pmsProductImages=pmsProductImageMapper.select(pmsProductImage);

        return pmsProductImages;
    }
}
