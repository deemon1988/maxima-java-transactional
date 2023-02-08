package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatService {
   @Autowired
   private CatRepository repo;

    public void printAllCats(){
        repo.findAll().forEach(System.out::println);
    }
    @Transactional
    public void  saveSomeCats(List<Cat> cats){
        for (Cat cat: cats) {
            repo.save(cat);
        }
    }
}
