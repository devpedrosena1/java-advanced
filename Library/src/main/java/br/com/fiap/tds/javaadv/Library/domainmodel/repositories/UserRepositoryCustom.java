package br.com.fiap.tds.javaadv.Library.domainmodel.repositories;

    public interface UserRepositoryCustom<User, UUID> {

        public User fetchByEmail(String email);
    }
