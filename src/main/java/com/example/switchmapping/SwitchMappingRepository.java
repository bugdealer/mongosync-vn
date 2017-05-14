package com.example.switchmapping;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;




public interface SwitchMappingRepository extends MongoRepository<SwitchMapping, String>{
	
	public List<SwitchMapping> findAll();
    public SwitchMapping save(SwitchMapping SwitchMapping);
	public <S extends SwitchMapping> List<S> save(Iterable<S> s);
	public List<SwitchMapping> findByVnId(int vnId);
	public List<SwitchMapping> findByNode(int node);


	
	public void deleteAll();
	public int deleteByVnId(int vnId);

}
