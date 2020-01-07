package club.huangliang.hmall.service;

import club.huangliang.hmall.pojo.PmsBaseCatalog1;
import club.huangliang.hmall.pojo.PmsBaseCatalog2;
import club.huangliang.hmall.pojo.PmsBaseCatalog3;

import java.util.List;

public interface CatalogService {
    List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
