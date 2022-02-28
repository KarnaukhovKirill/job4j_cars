package ru.job4j.store;

import ru.job4j.model.Engine;
import java.util.List;

public class EngineRepository {

    private EngineRepository() {

    }

    private static final class Holder {
        private static final EngineRepository INTS = new EngineRepository();
    }

    public static EngineRepository getInstance()  {
        return EngineRepository.Holder.INTS;
    }

    public List<Engine> findAllEngines() {
        return Store.getInstance().tx(session ->
                session.createQuery("from Engine").getResultList());
    }
}
