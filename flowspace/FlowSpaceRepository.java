package com.example.flowspace;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;




public interface FlowSpaceRepository extends MongoRepository<FlowSpace, String>{
	
	public List<FlowSpace> findAll();
    public FlowSpace save(FlowSpace flowSpace);
    public <S extends FlowSpace> List<S> save(Iterable<S> s);
	public List<FlowSpace> findByVnId(int vnId);

	
	
	public void deleteAll();
	public int deleteByVnId(int vnId);

}
