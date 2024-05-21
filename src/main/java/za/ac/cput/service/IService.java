package za.ac.cput.service;
public interface IService <T, ID>{
    T  save(T t);
    ID read (ID id);
    boolean delete(ID id);

}