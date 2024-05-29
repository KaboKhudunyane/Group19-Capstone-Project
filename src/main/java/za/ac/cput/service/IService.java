package za.ac.cput.service;

public interface IService<T, ID> {
    // Method to create a new entity
    T create(T t);
    // Method to read an entity by its ID
    T read(ID id);
    // Method to save an existing entity
    T update(T t);
}
