package club.huangliang.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("club.huangliang.manage.mapper")
public class HmallManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HmallManageServiceApplication.class, args);
    }

}
