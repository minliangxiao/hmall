package club.huangliang.manage.service.impl;

import club.huangliang.hmall.pojo.PmsBaseAttrInfo;
import club.huangliang.hmall.pojo.PmsBaseAttrValue;
import club.huangliang.hmall.pojo.PmsBaseSaleAttr;
import club.huangliang.hmall.service.AttrService;
import club.huangliang.manage.mapper.PmsBaseAttrInfoMapper;
import club.huangliang.manage.mapper.PmsBaseAttrValueMapper;
import club.huangliang.manage.mapper.PmsBaseSaleAttrMapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {
    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;
    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;


    public List<PmsBaseAttrInfo> attrinfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);
        for (PmsBaseAttrInfo baseAttrInfo : pmsBaseAttrInfos) {
            //
            List<PmsBaseAttrValue> pmsBaseAttrValues =new ArrayList<>();
            PmsBaseAttrValue pmsBaseAttrValue=new PmsBaseAttrValue();
            pmsBaseAttrValue.setAttrId(baseAttrInfo.getId());
            pmsBaseAttrValues=pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
            baseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        }

        return pmsBaseAttrInfos;
    }

    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        /*
         * 这儿有点缺点，需要用一个try catch语句捕获异常
         * */
//        保存属性（PmsBaseAttrInfo配置了主键返回策略，则通用mapper保存这个属性的时候会返回这个主键）
        String id = pmsBaseAttrInfo.getId();
        if (StringUtils.isBlank(id)) {
            //id为空则为保存
            pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);//insert会插入空值 insertSelective 是不将null插入到数据库
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
            }
        } else {
                //id不为空，修改
//            修改条件的建立
            Example e=new Example(PmsBaseAttrInfo.class);
            e.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());//根据id来修改
            pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo,e);//e表示原始数据，pmsBaseAttrInfo表示目标数据
            //修改属性值(这儿有点巧妙，但是也有一个问题我想不通，为什么不直接用通用mapper的update()语句)
            //这儿的删除策略是将AttrId为pmsBaseAttrInfo.getId()的所有PmsBaseAttrValue删除，然后再将数据 入进去相对于删除原有数据再添加新的数据。
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            PmsBaseAttrValue pmsBaseAttrValuedel=new PmsBaseAttrValue();
            pmsBaseAttrValuedel.setAttrId(pmsBaseAttrInfo.getId());
            pmsBaseAttrValueMapper.delete(pmsBaseAttrValuedel);

            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
            }

        }


        return "success";
    }
/*获取平台属性值*/
    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
//        定义一个PmsBaseAttrValue类，然后将查询规则放进这个类，然后查询时将这个类当作参数传进去
        PmsBaseAttrValue pmsBaseAttrValue=new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        List<PmsBaseAttrValue> pmsBaseAttrValueList=pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
        return pmsBaseAttrValueList;
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {

        return pmsBaseSaleAttrMapper.selectAll();
    }


}
