package br.com.fiap.tds.javaadv.Library.infrastructure.config;

import br.com.fiap.tds.javaadv.Library.domainmodel.User;
import br.com.fiap.tds.javaadv.Library.domainmodel.repositories.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            List<User> users = new LinkedList<>();
            Faker faker = new Faker();

            for(int i = 0; i < 1000; i++) {
                User user = new User(
                        UUID.randomUUID(),
                        faker.name().fullName(),
                        faker.internet().emailAddress(),
                        faker.internet().password(8, 16)
                );
                System.out.println(user);
                userRepository.save(user);
            }

            User loginUser = new User(
                    UUID.randomUUID(),
                    "user",
                    "user@gmail.com",
                    "0123456789"
            );
            userRepository.save(loginUser);
        };
    }
}
