package mk.ukim.finki.webprogramming.service.impl;

import mk.ukim.finki.webprogramming.service.ManufacturerService;
import mk.ukim.finki.webprogramming.model.Manufacturer;
import mk.ukim.finki.webprogramming.repository.InMemoryManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final InMemoryManufacturerRepository inMemoryManufacturerRepository;

    public ManufacturerServiceImpl(InMemoryManufacturerRepository inMemoryManufacturerRepository) {
        this.inMemoryManufacturerRepository = inMemoryManufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.inMemoryManufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        return inMemoryManufacturerRepository.save(name,address);
    }

    @Override
    public boolean deleteById(Long id) {
        return inMemoryManufacturerRepository.deleteById(id);
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.inMemoryManufacturerRepository.findById(id);
    }
}
