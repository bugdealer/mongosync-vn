package com.example.tablemapping;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;




public interface TableMappingRepository extends MongoRepository<TableMapping, String>{
	
	public List<TableMapping> findAll();
    public TableMapping save(TableMapping TableMapping);
	public <S extends TableMapping> List<S> save(Iterable<S> s);
	public List<TableMapping> findByVnId(int vnId);
	public List<TableMapping> findByNodeId(int nodeId);


	
	public void deleteAll();
	public int deleteByVnId(int vnId);

}
