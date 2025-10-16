package dev.gabrielt.microservices.authentication_service.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StatusRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${spring.datasource.database_name}")
    private String databaseName;

    public String getDatabaseHealth(){
        String querySql = "SELECT 1;";

        List result = List.of();

        try {
            result = executeQuery(querySql);
        }catch (Exception e){
            return "Unhealthy";
        }

        return "Healthy";
    }

    public String getDatabaseVersion(){
        String querySql = "SHOW server_version;";

        List result;

        try{
            result = executeQuery(querySql);
        }catch (Exception e){
            return "Unavailable";
        }

        return result.getFirst().toString().subSequence(0, 5).toString();
    }

    public String getDatabaseMaxConnections(){
        String querySql = "SHOW max_connections;";

        List result;

        try{
            result = executeQuery(querySql);
        }catch (Exception e){
            return "Unavailable";
        }

        return  result.getFirst().toString();
    }

    public String getDatabaseOpenedConnections(){
        String querySql = "SELECT count(*)::int FROM pg_stat_activity WHERE datname = ?1;";

        List result;

        try{
            Query nativeQuery = entityManager.createNativeQuery(querySql);
            nativeQuery.setParameter(1, databaseName);
            result = executeQuery(nativeQuery);
        }catch (Exception e){
            return "Unavailable";
        }
        return  result.getFirst().toString();
    }

    private List executeQuery(String querySql){
        Query nativeQuery = entityManager.createNativeQuery(querySql);
        return nativeQuery.getResultList();
    }

    private List executeQuery(Query nativeQuery){
        return nativeQuery.getResultList();
    }


}
