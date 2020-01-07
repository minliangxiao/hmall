package club.huangliang.hmalluser.service.impl;

import club.huangliang.hmall.pojo.UmsMember;
import club.huangliang.hmall.pojo.UmsMemberReceiveAddress;
import club.huangliang.hmall.service.UserService;
import club.huangliang.hmalluser.mapper.UmsMemberReceiveAddressMapper;
import club.huangliang.hmalluser.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    @Override
    public List<UmsMember> getAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
//       根据umsMemberReceiveAddress对象的封装的不为空的字段经行查询，将擦选的结果返回到 List<UmsMemberReceiveAddress> umsMemberReceiveAddresses里面然后返回回去
        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);
//        .selectByExample(查询条件)这种查询方式可以实现自定义查询方法
//        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressMapper.selectByExample(umsMemberReceiveAddress);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressMapper.select(umsMemberReceiveAddress);
        return umsMemberReceiveAddresses;
    }
}
