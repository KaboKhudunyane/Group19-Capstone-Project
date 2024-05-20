package za.ac.cput.service;
public interface IService <T, ID>{
    T create(T t);
    ID read (ID id);
    T update(T t);
}