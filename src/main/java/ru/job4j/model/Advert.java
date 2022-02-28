package ru.job4j.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "adverts")
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "USER_ID_FK"), nullable = false, updatable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", foreignKey = @ForeignKey(name = "CAR_ID_FK"), nullable = false, updatable = false)
    private Car car;
    private String description;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", foreignKey = @ForeignKey(name = "PHOTO_ID_FK"), unique = true)
    private Photo photo;
    private boolean sold;

    public Advert() {
    }

    public static Advert of(User user, Car car, String description) {
        Advert advert = new Advert();
        advert.setDate(new Date(System.currentTimeMillis()));
        advert.setUser(user);
        advert.setCar(car);
        advert.setDescription(description);
        advert.setSold(false);
        return advert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advert advert = (Advert) o;
        return id == advert.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Advert{"
                + "id=" + id
                + ", date=" + date
                + ", user=" + user
                + ", car=" + car
                + ", description='" + description + '\''
                + ", photo=" + photo
                + ", sold=" + sold
                + '}';
    }
}
