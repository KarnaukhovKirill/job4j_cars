package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Advert;
import ru.job4j.model.Brand;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class AdRepository {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private AdRepository() {

    }

    private static class Holder {
        private static final AdRepository INST = new AdRepository();
    }

    public static AdRepository getInstance() {
        return Holder.INST;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Advert> getAdLastDay() {
        return tx(session -> {
            Date date = new Date();
            date.setHours(date.getHours() - 24);
            var query = session.createQuery("select distinct a from Advert a "
                    + "join fetch a.car c "
                            + "join fetch c.users "
                            + "join fetch c.modelCar "
                            + "join fetch c.brand "
                            + "join fetch c.bodyCar "
                    + "join fetch c.engine "
                    + "join fetch a.user "
                    + "join fetch a.photo "
                    + "where a.date > :date");
            query.setParameter("date", date);
            List<Advert> adverts = query.getResultList();
            return adverts;
        });
    }

    public List<Advert> getAdWithPhoto() {
        return tx(session -> {
            var query = session.createQuery("select distinct a from Advert a "
                    + "join fetch a.car c "
                            + "join fetch c.users "
                            + "join fetch c.modelCar "
                            + "join fetch c.brand "
                            + "join fetch c.bodyCar "
                            + "join fetch c.engine "
                    + "join fetch a.user "
                    + "join fetch a.photo "
                    + "where a.photo is not null");
            List<Advert> adverts = query.getResultList();
            return adverts;
        });
    }

    public List<Advert> getAdBrand(Brand brand) {
        return tx(session -> {
            var query = session.createQuery("select distinct a from Advert a "
                    + "join fetch a.car c "
                            + "join fetch c.users "
                            + "join fetch c.modelCar "
                            + "join fetch c.brand "
                            + "join fetch c.bodyCar "
                            + "join fetch c.engine "
                    + "join fetch a.user "
                    + "join fetch a.photo "
                    + "where c.brand = :brand");
            query.setParameter("brand", brand);
            List<Advert> adverts = query.getResultList();
            return adverts;
        });
    }
}
