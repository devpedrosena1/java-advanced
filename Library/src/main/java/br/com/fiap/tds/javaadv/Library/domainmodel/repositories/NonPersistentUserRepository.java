package br.com.fiap.tds.javaadv.Library.domainmodel.repositories;

import br.com.fiap.tds.javaadv.Library.domainmodel.User;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class NonPersistentUserRepository implements UserRepository<User, UUID> {

    // database in memory
    private List<User> internalData = new ArrayList<>();

    public NonPersistentUserRepository() {
        Faker faker = new Faker();

        for(int i = 0; i < 50; i++) {
            User user = new User(
                    UUID.randomUUID(),
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.internet().password(8, 16)
            );
            internalData.add(user);
        }
    }

    // method 1 -> return users in list format
    @Override
    public List<User> findAll() {
        return this.internalData.stream().toList();
    }

    // method 2 -> return user for id
    @Override
    public User findById(UUID uuid) {
        User sampleUser = new User(uuid);

        if (this.internalData.contains(sampleUser))
            return this.internalData.get(this.internalData.indexOf(sampleUser));
        else
            throw new IllegalArgumentException("User not found on internal array!");
    }

    // method 3 -> create user on array
    @Override
    public User create(User user) {
       this.internalData.add(user);
       return user;
    }

    // method 4 -> user exist in array for id
    @Override
    public boolean existsById(UUID uuid) {
        User sampleUser = new User();
        return this.internalData.contains(sampleUser);
    }

    // method 5 -> remove user in database
    @Override
    public void removeById(UUID uuid) {
        User user = this.findById(uuid);
        this.internalData.remove(this.internalData.indexOf(user));

    }
}
