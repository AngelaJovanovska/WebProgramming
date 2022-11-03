package mk.ukim.finki.webprogramiranje.Service.impl;

import mk.ukim.finki.webprogramiranje.Service.CategoryService;
import mk.ukim.finki.webprogramiranje.model.Category;
import mk.ukim.finki.webprogramiranje.repository.InMemoryCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
//anotacija za da se startuva na samiot pocetok koga se startuva Springboot app

@Service
public class CategoryServiceImpl implements CategoryService {

    //service slojot e zavisen od data(access) slojot i dependecy se stava
    //dependecy injection nataka kje se zbori
    private final InMemoryCategoryRepository categoryRepository;

    //constructor injection se povikuva pri start na app
    public CategoryServiceImpl(InMemoryCategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(String name, String description) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public Category update(String name, String description) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public void delete(String name) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        categoryRepository.delete(name);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepository.search(searchText);
    }
}
