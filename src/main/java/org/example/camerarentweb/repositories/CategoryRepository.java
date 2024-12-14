package org.example.camerarentweb.repositories;

import org.example.camerarentweb.entities.Category;

public interface CategoryRepository extends BaseRepository<Category>{
    Category categoryByName(String name);
}
