package club.huangliang.hmall.service;

import club.huangliang.hmall.pojo.UmsMember;
import club.huangliang.hmall.pojo.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);

}
