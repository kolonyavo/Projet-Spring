package com.example.schoolAPI.repository;

import com.example.schoolAPI.model.Groups;
import com.example.schoolAPI.model.Students;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsRepository extends Groups{
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/school", "postgres", "kolonyavo");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Students> findAll(){
        List<Students> students = new ArrayList<>();
        try{
            PreparedStatement pStm = getConnection().prepareStatement("select * from students");

            ResultSet res = pStm.executeQuery();

            while (res.next()){
                students.add(new Students(
                        res.getLong("id"),
                        res.getString("name"),
                        res.getObject("groups")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }

    public Students save(Students s){
        Students newStudents = new Students();
        try {
            PreparedStatement pStm = getConnection().prepareStatement("INSERT INTO students VALUES (?, ?)");
            pStm.setString(1, newStudents.getName());
            pStm.setObject(2, newStudents.getGroups());
            ResultSet res = pStm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newStudents;
    }

    public void deletByID(Long id) {
        try {
            PreparedStatement del = getConnection().prepareStatement("DELETE FROM students WHERE id=?");
            ResultSet result = del.executeQuery();
            result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  Students updateNameById(Long id, String newName){
        Students s = new Students();
        try {
            PreparedStatement update = getConnection().prepareStatement("UPDATE students SET name= newName WHERE id=?");
            update.setLong(1, id);
            ResultSet res = update.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public List<Students>  findWhereNameLike(String query){
        List<Students> students = new ArrayList<>();
        try{
            PreparedStatement pStm = getConnection().prepareStatement("select * from students where name like '%'+?+'%'");
            pStm.setString(1, query);
            ResultSet res = pStm.executeQuery();

            while (res.next()){
                students.add(new Students(
                        res.getLong("id"),
                        res.getString("name"),
                        res.getObject("groups")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }
}
//implémentez cette interface avec une classe concrète avec JDBC
// Devoir recherche sur google : proposez une autre implémentation avec JPQL (Java Persistence Query) language (vous pouvez voir l'annotation : @Query)

/* List<Students> findAll();

    Students save(Students s);

    void deleteById(Long id);

    Students updateNameById(Long id, String newName);

    List<Students> findWhereNameLike(String query);
    //select * from students where name like '%query%'
}*/

