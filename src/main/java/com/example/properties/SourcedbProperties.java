package com.example.properties;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SourcedbProperties {

	@Value("${sourcedb.address}")
	private String address;
	@Value("${sourcedb.port}")
	private String port;
	@Value("${sourcedb.database}")
	private String database;
	@Value("${sourcedb.collection}")
	private String collection;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	
	
	
}
