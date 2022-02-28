package ru.job4j.store;

import ru.job4j.model.*;

public class CarRepository {

    private CarRepository() {

    }

    private static final class Holder {
        private static final CarRepository INTS = new CarRepository();
    }

    public static CarRepository getInstance()  {
        return Holder.INTS;
    }

    public Car create(int userId, String year, String vin, int engineId, int bodyId, int brandId, int modelId) {
        return Store.getInstance().tx(session -> {
            var body = session.load(BodyCar.class, bodyId);
            var brand = session.load(Brand.class, brandId);
            var engine = session.load(Engine.class, engineId);
            var model = session.load(ModelCar.class, modelId);
            Car car = Car.of(vin, year, engine, body, brand, model);
            car.addUser(session.get(User.class, userId));
            session.persist(car);
            return car;
        });
    }
}
