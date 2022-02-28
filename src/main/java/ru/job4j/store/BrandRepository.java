package ru.job4j.store;

import ru.job4j.model.Brand;
import java.util.List;

public class BrandRepository {

    private BrandRepository() {

    }

    private static final class Holder {
        private static final BrandRepository INTS = new BrandRepository();
    }

    public static BrandRepository getInstance()  {
        return BrandRepository.Holder.INTS;
    }

    public List<Brand> findAllBrands() {
        return Store.getInstance().tx(session -> session.createQuery("select distinct b from Brand b")
                .getResultList());
    }
}
