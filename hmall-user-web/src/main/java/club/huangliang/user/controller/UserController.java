package club.huangliang.user.controller;

import club.huangliang.hmall.pojo.UmsMember;
import club.huangliang.hmall.pojo.UmsMemberReceiveAddress;

import club.huangliang.hmall.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Reference
    private UserService userService;

    @RequestMapping("test")
    @ResponseBody
    public String test() {
        return "this is test ";
    }

    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser() {
        List<UmsMember> umsMember = userService.getAllUser();
        return umsMember;
    }

    @RequestMapping("getReceiveAddressByMemberId")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = userService.getReceiveAddressByMemberId(memberId);
        return umsMemberReceiveAddresses;
    }

}
