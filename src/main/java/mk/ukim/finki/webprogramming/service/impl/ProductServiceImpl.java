package mk.ukim.finki.webprogramming.service.impl;

import mk.ukim.finki.webprogramming.service.ProductService;
import mk.ukim.finki.webprogramming.model.Category;
import mk.ukim.finki.webprogramming.model.Manufacturer;
import mk.ukim.finki.webprogramming.model.Product;
import mk.ukim.finki.webprogramming.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.webprogramming.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.webprogramming.repository.InMemoryCategoryRepository;
import mk.ukim.finki.webprogramming.repository.InMemoryManufacturerRepository;
import mk.ukim.finki.webprogramming.repository.InMemoryProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final InMemoryProductRepository inMemoryProductRepository;
    private final InMemoryCategoryRepository inMemoryCategoryRepository;
    private final InMemoryManufacturerRepository inMemoryManufacturerRepository;

    public ProductServiceImpl(InMemoryProductRepository inMemoryProductRepository, InMemoryCategoryRepository inMemoryCategoryRepository, InMemoryManufacturerRepository inMemoryManufacturerRepository) {
        this.inMemoryProductRepository = inMemoryProductRepository;
        this.inMemoryCategoryRepository = inMemoryCategoryRepository;
        this.inMemoryManufacturerRepository = inMemoryManufacturerRepository;
    }

    @Override
    public List<Product> findAll() {
        return inMemoryProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return inMemoryProductRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return inMemoryProductRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = this.inMemoryCategoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryNotFoundException(categoryId));

        Manufacturer manufacturer = this.inMemoryManufacturerRepository.findById(manufacturerId)
                .orElseThrow(()->new ManufacturerNotFoundException(manufacturerId));

        return this.inMemoryProductRepository.save(name,price,quantity,category,manufacturer);
    }

    @Override
    public void deleteById(Long id) {
        inMemoryProductRepository.deleteById(id);
    }
}
