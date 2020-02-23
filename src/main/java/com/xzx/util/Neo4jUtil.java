package com.xzx.util;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Neo4jUtil {

    private static Driver driver;


    @Autowired
    public Neo4jUtil(Driver driver) {
        Neo4jUtil.driver = driver;
    }

    public static Session getSession() {
        Session session = driver.session();

        //System.out.println(cql);
        //Result result = session.run(cql);
        //System.out.println("success");
        //session.close();
        //driver.close();
        return session;
    }
}
