package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CatRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PostConstruct
    public void init(){
        jdbcTemplate.execute("CREATE TABLE  cats (" +
                "name VARCHAR(6), weight INT)");
        jdbcTemplate.update("INSERT  INTO  cats(name, weight) VALUES (?,?)", "Мурзик",10);
        jdbcTemplate.update("INSERT INTO cats(name, weight) VALUES (?,?)","Барсик", 5);
    }

    public List<Cat> findAll(){
        return new ArrayList<>(
                jdbcTemplate.query("SELECT * FROM cats", new BeanPropertyRowMapper<>(Cat.class))
        );
    }
public void save(Cat cat){
        String query = "INSERT INTO cats(name, weight) VALUES (?,?)";
        Object[] args = new Object[] {cat.getName(), cat.getWeight()};
        int out = jdbcTemplate.update(query, args);
        if (out>0){
            System.out.println("Cat saved.");
        }
        else {
            System.out.println("Error saving cat");
        }
}
}

