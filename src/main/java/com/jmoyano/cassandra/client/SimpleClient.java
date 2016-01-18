package com.jmoyano.cassandra.client;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;

/**
 * Hello world!
 *
 */
@Component
public class SimpleClient 
{
	private Cluster cluster;
	
	private Session session;
	
	@Value("${cluster.hosts}")
	private String hosts;
	
	@PostConstruct
	public void init(){
		cluster = Cluster.builder().addContactPoints(hosts.split(",")).build();
		session = cluster.connect();
	}
    
    public void setHosts(String hosts){
    	this.hosts = hosts; 
    }
    
    @PreDestroy
    public void close(){
    	this.cluster.close();
    }
    
    public ResultSet execute(String cql){
    	return session.execute(cql);
    }
    
    public ResultSet execute(Statement cql){
    	return session.execute(cql);
    }
    
    public ResultSetFuture executeAsync(Statement cql){
    	return session.executeAsync(cql);
    }
    
    public PreparedStatement getPreparedStatement(String query){
    	return session.prepare(query);
    }
}
