package ru.job4j.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String production;
    @Column(unique = true, nullable = false, length = 17)
    private String vin;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"), nullable = false)
    private Engine engine;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dobyCar_id", foreignKey = @ForeignKey(name = "BODYCAR_ID_FK"), nullable = false)
    private BodyCar bodyCar;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", foreignKey = @ForeignKey(name = "BRAND_ID_FK"), nullable = false)
    private Brand brand;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "modelCar_id", foreignKey = @ForeignKey(name = "MODELCAR_ID_FK"), nullable = false)
    private ModelCar modelCar;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "car_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false)})
    private Set<User> users = new HashSet<>();

    public Car() {
    }

    public static Car of(String vin, String date, Engine engine, BodyCar bodyCar, Brand brand, ModelCar modelCar) {
        Car car = new Car();
        car.setVin(vin);
        car.setProduction(date);
        car.setEngine(engine);
        car.setBodyCar(bodyCar);
        car.setBrand(brand);
        car.setModelCar(modelCar);
        return car;
    }

    public void addUser(User user) {
        this.getUsers().add(user);
    }

    public int getId() {
        return id;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public BodyCar getBodyCar() {
        return bodyCar;
    }

    public void setBodyCar(BodyCar bodyCar) {
        this.bodyCar = bodyCar;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ModelCar getModelCar() {
        return modelCar;
    }

    public void setModelCar(ModelCar modelCar) {
        this.modelCar = modelCar;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id='" + id + '\''
                + ", production=" + production
                + ", engine=" + engine
                + ", bodyCar=" + bodyCar
                + ", brand=" + brand
                + ", modelCar=" + modelCar
                + ", users=" + users
                + '}';
    }
}
