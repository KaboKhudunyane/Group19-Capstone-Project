package za.ac.cput.service;
import java.util.List;
public interface IService<T, ID> {
    // Method to create a new entity
    T create(T t);
    // Method to read an entity by its ID
    T read(ID id);
    // Method to update an existing entity
    T update(T t);
    // Method to delete an entity by its ID
    void delete(ID id);
    // Method to get all entities
    List<T> getAll();
}