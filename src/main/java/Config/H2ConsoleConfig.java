package Config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class H2ConsoleConfig {

    @Bean
    public Server h2Console() throws SQLException {
        System.out.println("--- Запуск H2 Console на порту 8085 ---");
        Server server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        server.start();
        System.out.println("--- H2 Console запущено: http://localhost:8082 ---");
        return server;
    }
}