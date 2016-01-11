package com.jmoyano.cassandra.client;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

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
}
