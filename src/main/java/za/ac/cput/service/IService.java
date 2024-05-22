package za.ac.cput.service;
public interface IService <T, ID>{
    T create(T t);
    T  save(T t);
    ID read (ID id);

}