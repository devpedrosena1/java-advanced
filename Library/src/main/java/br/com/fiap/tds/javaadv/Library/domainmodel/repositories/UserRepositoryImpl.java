package br.com.fiap.tds.javaadv.Library.domainmodel.repositories;

import br.com.fiap.tds.javaadv.Library.domainmodel.QUser;
import br.com.fiap.tds.javaadv.Library.domainmodel.User;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Setter;

import java.util.UUID;

public class UserRepositoryImpl implements UserRepositoryCustom<User, UUID> {

    @PersistenceContext
    private @Setter EntityManager entityManager;

    @Override
    public User fetchByEmail(String email) {
        JPAQueryFactory factory = new JPAQueryFactory(entityManager);
        QUser user = QUser.user;

        JPAQuery<User> query = factory
                .select(user)
                .where(
                        user.email.eq(email)
                );
        return query.fetchFirst();
    }
}
