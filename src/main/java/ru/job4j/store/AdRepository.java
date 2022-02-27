package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.*;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class AdRepository {

    private AdRepository() {

    }

    private static final class Holder {
        private static final AdRepository INST = new AdRepository();
    }

    public static AdRepository getInstance()  {
        return Holder.INST;
    }

    public Advert create(int userId, int carId, String description) {
        return Store.getInstance().tx(session -> {
            var user = session.find(User.class, userId);
            var car = session.find(Car.class, carId);
            Advert advert = Advert.of(user, car, description);
            session.persist(advert);
            return advert;
        });
    }

    public void delete(int id) {
        Store.getInstance().tx(session -> {
            Advert advert = new Advert();
            advert.setId(id);
            session.delete(advert);
            return null;
        });
    }

    public void setPhoto(int id, Photo photo) {
        Store.getInstance().tx(session -> {
            Advert advert = session.get(Advert.class, id);
            advert.setPhoto(photo);
            session.saveOrUpdate(advert);
            return null;
        });
    }

    public List<Advert> getByUser(User user) {
        return Store.getInstance().tx(session -> {
            var query = session.createQuery("select a from Advert a "
                    + "join fetch a.car c "
                        + "join fetch c.users "
                        + "join fetch c.modelCar "
                        + "join fetch c.brand "
                        + "join fetch c.bodyCar "
                        + "join fetch c.engine "
                    + "join fetch a.user u "
                    + "join fetch a.photo "
                    + "where u = :user "
                    + "and a.sold = false");
            query.setParameter("user", user);
            List<Advert> adverts = query.getResultList();
            return adverts;
        });
    }

    public List<Advert> getAdLastDay() {
        return Store.getInstance().tx(session -> {
            Date date = new Date();
            date.setHours(date.getHours() - 24);
            var query = session.createQuery("select a from Advert a "
                    + "join fetch a.car c "
                            + "join fetch c.brand "
                            + "join fetch c.bodyCar "
                            + "join fetch c.engine "
                            + "join fetch c.users "
                            + "join fetch c.modelCar "
                    + "join fetch a.user "
                    + "join fetch a.photo "
                    + "where a.date > :date");
            query.setParameter("date", date);
            List<Advert> adverts = query.getResultList();
            return adverts;
        });
    }

    public List<Advert> getAdWithPhoto() {
        return Store.getInstance().tx(session -> {
            var query = session.createQuery("select a from Advert a "
                    + "join fetch a.car c "
                        + "join fetch c.brand "
                        + "join fetch c.bodyCar "
                        + "join fetch c.engine "
                        + "join fetch c.users "
                        + "join fetch c.modelCar "
                    + "join fetch a.user "
                    + "join fetch a.photo "
                    + "where a.photo is not null "
                    + "and a.sold = false");
            List<Advert> adverts = query.getResultList();
            return adverts;
        });
    }

    public List<Advert> getAdBrand(Brand brand) {
        return Store.getInstance().tx(session -> {
            var query = session.createQuery("select a from Advert a "
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

    public List<Advert> getNotSold() {
        return Store.getInstance().tx(session -> {
            var query = session.createQuery("select a from Advert a "
                    + "join fetch a.car c "
                        + "join fetch c.brand "
                        + "join fetch c.bodyCar "
                        + "join fetch c.engine "
                        + "join fetch c.users "
                        + "join fetch c.modelCar "
                    + "join fetch a.user "
                    + "join fetch a.photo "
                    + "where a.sold = false");
            List<Advert> adverts = query.getResultList();
            return adverts;
        });
    }
}
