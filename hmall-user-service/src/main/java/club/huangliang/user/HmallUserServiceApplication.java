package club.huangliang.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "club.huangliang.user.mapper")
public class HmallUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HmallUserServiceApplication.class, args);
    }

}
