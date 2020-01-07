package club.huangliang.hmalluser;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;//t通用mapper要求的包扫描器

@SpringBootApplication
@MapperScan(basePackages = "club.huangliang.hmalluser.mapper")//这是告诉spring boot自动去扫描mapper类
public class HmallUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(HmallUserApplication.class, args);
    }

}
