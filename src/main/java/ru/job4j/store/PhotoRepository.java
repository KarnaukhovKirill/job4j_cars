package ru.job4j.store;

import ru.job4j.model.Photo;

public class PhotoRepository {

    private PhotoRepository() {

    }

    private static final class Holder {
        private static final PhotoRepository INTS = new PhotoRepository();
    }

    public static PhotoRepository getInstance()  {
        return PhotoRepository.Holder.INTS;
    }

    public Photo create(String name) {
        return Store.getInstance().tx(session -> {
            Photo photo = Photo.of(name);
            session.persist(photo);
            return photo;
        });
    }

    public Photo delete(int id) {
        return Store.getInstance().tx(session -> {
            var photo = session.get(Photo.class, id);
            session.delete(photo);
            return photo;
        });
    }

}
