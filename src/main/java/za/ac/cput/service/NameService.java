package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Name;
import za.ac.cput.repository.NameRepository;

import java.util.List;

@Service
public class NameService implements IService<Name, String> {

    private final NameRepository nameRepository;

    @Autowired
    public NameService(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    @Override
    public Name create(Name name) {
        return nameRepository.save(name);
    }

    @Override
    public Name read(String id) {
        // Since firstName is the primary key, find by firstName
        return nameRepository.findByFirstName(id);
    }

    @Override
    public Name update(Name name) {
        return nameRepository.save(name);
    }

    public void delete(String id) {
        // Since firstName is the primary key, delete by firstName
        nameRepository.deleteByFirstName(id);
    }

    public List<Name> getAllNames() {
        return nameRepository.findAll();
    }
}
