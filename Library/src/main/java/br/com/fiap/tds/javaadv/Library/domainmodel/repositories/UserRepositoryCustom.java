package br.com.fiap.tds.javaadv.Library.domainmodel.repositories;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom<User, UUID> {

        public User fetchByEmail(String email);

        public Optional<User> findByIdWithProfileAndPostsCriteria(java.util.UUID id);

        public List<User> findByMinPostsAndNameLikeCriteria(int minPosts, String namePart);

        public List<User> findByMinPostsAndNameLikeQdsl(int minPosts, String namePart);

        public Optional<User> findByIdWithProfileAndPostsQdsl(UUID id);
    }
