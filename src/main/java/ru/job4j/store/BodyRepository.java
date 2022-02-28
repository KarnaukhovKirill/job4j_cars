package ru.job4j.store;

import ru.job4j.model.BodyCar;
import java.util.List;

public class BodyRepository {

    private BodyRepository() {

    }

    private static final class Holder {
        private static final BodyRepository INTS = new BodyRepository();
    }

    public static BodyRepository getInstance()  {
        return BodyRepository.Holder.INTS;
    }

    public List<BodyCar> findAllBodies() {
        return Store.getInstance().tx(session ->
                session.createQuery("from BodyCar").getResultList());
    }
}
