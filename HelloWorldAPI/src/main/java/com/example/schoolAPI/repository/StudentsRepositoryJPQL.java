package com.example.schoolAPI.repository;

import com.example.schoolAPI.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentsRepositoryJPQL extends JpaRepository<Students, Long> {
    @Query(value = "SELECT * FROM students", nativeQuery = true)
    List<Students> findAll();

    @Query(value = "INSERT INTO Students (NAME, Groups) VALUES (:name, :groups)")
    Students save(Students s);

    @Query(value = "delete from Students s where s.id = ?1")
    void deleteById(Long id);

    @Modifying
    @Query("update Students s set s.name = ?1 where s.id = ?2")
    Students updateNameById(Long id, String newName);

    @Query(value = "SELECT S FROM Students S WHERE S.name like concat('%',:name,'%')")
    List<Students> findWhereNameLike(@Param("name") String query);
}
