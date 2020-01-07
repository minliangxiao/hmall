package club.huangliang.manage.service.impl;

import club.huangliang.hmall.pojo.PmsBaseCatalog1;
import club.huangliang.hmall.pojo.PmsBaseCatalog2;
import club.huangliang.hmall.pojo.PmsBaseCatalog3;
import club.huangliang.hmall.service.CatalogService;
import club.huangliang.manage.mapper.PmsBaseCatalog1Mapper;
import club.huangliang.manage.mapper.PmsBaseCatalog2Mapper;
import club.huangliang.manage.mapper.PmsBaseCatalog3Mapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;
    @Autowired
    PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;
    @Autowired
    PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;
    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        List<PmsBaseCatalog1> catalog1s = pmsBaseCatalog1Mapper.selectAll();
        return catalog1s;
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        PmsBaseCatalog2 pmsBaseCatalog2=new PmsBaseCatalog2();
        pmsBaseCatalog2.setCatalog1Id(catalog1Id);
        List<PmsBaseCatalog2> catalog2s = pmsBaseCatalog2Mapper.select(pmsBaseCatalog2);
        return catalog2s;
    }

    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        PmsBaseCatalog3 pmsBaseCatalog3=new PmsBaseCatalog3();
        pmsBaseCatalog3.setCatalog2Id(catalog2Id);
        List<PmsBaseCatalog3> catalog3s = pmsBaseCatalog3Mapper.select(pmsBaseCatalog3);
        return catalog3s;
    }

}
