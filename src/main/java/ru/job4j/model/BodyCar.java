package ru.job4j.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bodies")
public class BodyCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String name;

    public BodyCar() {
    }

    public static BodyCar of(String name) {
        BodyCar bodyCar = new BodyCar();
        bodyCar.setName(name);
        return bodyCar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BodyCar bodyCar = (BodyCar) o;
        return id == bodyCar.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BodyCar{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
