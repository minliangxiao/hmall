package club.huangliang.hmalluser.mapper;

import club.huangliang.hmall.pojo.UmsMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<UmsMember> {
    List<UmsMember> selectAllUser();

}
