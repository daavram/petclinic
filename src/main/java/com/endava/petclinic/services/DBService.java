package com.endava.petclinic.services;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.PetType;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.EnvReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class DBService {

    public Owner getOwnerById(Long id) {
        try (Connection conn = DriverManager.getConnection(EnvReader.getDBUrl(), EnvReader.getDBUsername(), EnvReader.getDBPassword())) {
            var mapColumnsToProperties = new HashMap<String, String>();
            //mapping you database to entity here;
            mapColumnsToProperties.put("first_name", "firstName");
            mapColumnsToProperties.put("last_name", "lastName");
            BeanProcessor beanProcessor = new BeanProcessor(mapColumnsToProperties);
            RowProcessor rowProcessor = new BasicRowProcessor(beanProcessor);
            ResultSetHandler<Owner> h = new BeanHandler<Owner>(Owner.class, rowProcessor);

            QueryRunner runner = new QueryRunner();
            Owner owner = runner.query(conn, "select * from owners where id = ?", h, id);
            return owner;
        } catch (SQLException e) {
            throw new RuntimeException("Can't connect to DB ", e);
        }
    }

    public PetType getPetTypeById(Long id) {
        try (Connection conn = DriverManager.getConnection(EnvReader.getDBUrl(), EnvReader.getDBUsername(), EnvReader.getDBPassword())) {
//            var mapColumnsToProperties = new HashMap<String, String>();
////            //mapping you database to entity here;
//            mapColumnsToProperties.put("name", "name");
//            BeanProcessor beanProcessor = new BeanProcessor(mapColumnsToProperties);
//            RowProcessor rowProcessor = new BasicRowProcessor(beanProcessor);
//            ResultSetHandler<PetType> h = new BeanHandler<PetType>(PetType.class, rowProcessor);
            ResultSetHandler<PetType> h = new BeanHandler<PetType>(PetType.class);

            QueryRunner runner = new QueryRunner();
            System.out.println(id);
            System.out.println(runner);
            PetType petType = runner.query(conn, "select * from petTypes where id = ?", h, id);
            return petType;
        } catch (SQLException e) {
            throw new RuntimeException("Can't connect to DB ", e);
        }
    }
}