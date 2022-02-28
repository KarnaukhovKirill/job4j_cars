package ru.job4j.store;

import ru.job4j.model.User;

public class UserRepository {
    private UserRepository() {

    }

    private static final class Holder {
        private static final UserRepository INTS = new UserRepository();
    }

    public static UserRepository getInstance()  {
        return UserRepository.Holder.INTS;
    }

    public User create(String name, String email, String password) {
        return Store.getInstance().tx(session -> {
            User user = User.of(name, email, password);
            session.persist(user);
            return user;
        });
    }

    public User findByEmail(String email) {
        return Store.getInstance().tx(session -> {
            var query = session.createQuery("from User where email = :email").setParameter("email", email);
            return (User) query.uniqueResult();
        });
    }
}
