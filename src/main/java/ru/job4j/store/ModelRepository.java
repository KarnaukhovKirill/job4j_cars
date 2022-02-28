package ru.job4j.store;

import ru.job4j.model.ModelCar;
import java.util.List;

public class ModelRepository {

    private ModelRepository() {

    }

    private static final class Holder {
        private static final ModelRepository INTS = new ModelRepository();
    }

    public static ModelRepository getInstance()  {
        return ModelRepository.Holder.INTS;
    }

    public List<ModelCar> findModels(int id) {
        return Store.getInstance().tx(session -> {
            var query = session.createQuery("select distinct m from ModelCar m "
                    + "join fetch m.brand b where b.id = :id");
            query.setParameter("id", id);
            return query.getResultList();
        });
    }
}
