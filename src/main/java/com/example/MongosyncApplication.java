package com.example;




import com.example.dpinventory.DpInventory;
import com.example.dpresource.DpResource;
import com.example.flowspace.FlowSpace;
import com.example.switchmapping.SwitchMapping;
import com.example.tablemapping.TableMapping;
import com.example.topo.Topo;
import com.example.vn.Vn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration(exclude={MongoDataAutoConfiguration.class,MongoRepositoriesAutoConfiguration.class})
@ComponentScan
public class MongosyncApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("nmsDbTemplate")
	MongoTemplate nmsTemplate;


	@Autowired
	@Qualifier("sourceDbTemplate")
	MongoTemplate sourceTemplate;
	
	@Autowired
	@Qualifier("vnDbTemplate")
	MongoTemplate vnTemplate;
   
	
	
	private List<Topo> cachedTopo = new ArrayList<Topo>();
	private List<Topo> pollTopo = new ArrayList<Topo>();
	
	private List<Vn> cachedVn = new ArrayList<Vn>();
	private List<Vn> pollVn = new ArrayList<Vn>();
	
	
	
	
	private List<Integer> runtimeConfig = new ArrayList<Integer>();
	private List<Integer> newConfig = new ArrayList<Integer>();
	
	private List<SwitchMapping> cachedSwitchMapping = new ArrayList<SwitchMapping>();
	private List<SwitchMapping> pollSwitchMapping = new ArrayList<SwitchMapping>();
	
	private List<FlowSpace> cachedFlowSpace = new ArrayList<FlowSpace>();
	private List<FlowSpace> pollFlowSpace = new ArrayList<FlowSpace>();
	
	
	private List<TableMapping> cachedTableMapping = new ArrayList<TableMapping>();
	private List<TableMapping> pollTableMapping = new ArrayList<TableMapping>();
	
	private List<DpInventory> cachedInventory = new ArrayList<DpInventory>();
	private List<DpInventory> pollInventory = new ArrayList<DpInventory>();
	
	private List<DpResource> cachedResource = new ArrayList<DpResource>();
	private List<DpResource> pollResource = new ArrayList<DpResource>();

	
	@Scheduled(fixedDelay = 5000)
	public void resourceSync(){
	
	try {
		pollResource = sourceTemplate.findAll(DpResource.class);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if(cachedResource.equals(pollResource))
	{
		
		System.out.println("No need to sync DpResource.");
	}
	else
	{
		nmsTemplate.dropCollection("dpResource");
		nmsTemplate.save(pollResource.iterator().next());
		cachedResource = nmsTemplate.findAll(DpResource.class);
		System.out.println("DpResource synced.");
		System.out.println("NMS DB: "+cachedResource.toString());
	
		
	}
	}
	
	@Scheduled(fixedDelay = 5000)
	public void inventorySync(){
	
	try {
		pollInventory = sourceTemplate.findAll(DpInventory.class);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if(cachedInventory.equals(pollInventory))
	{
		
		System.out.println("No need to sync DpInventory.");
	}
	else
	{
		pollInventory.removeAll(cachedInventory);
		nmsTemplate.save(pollInventory.iterator().next());
		cachedInventory = nmsTemplate.findAll(DpInventory.class);
		System.out.println("DpInventory synced.");
		System.out.println("NMS DB: "+cachedInventory.toString());
	
		
	}
	}
	
	
	@Scheduled(fixedDelay = 5000)
	protected void configSync(){
		try {
			pollTableMapping = nmsTemplate.findAll(TableMapping.class);
			pollSwitchMapping = nmsTemplate.findAll(SwitchMapping.class);
			pollFlowSpace = nmsTemplate.findAll(FlowSpace.class);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(cachedTableMapping.equals(pollTableMapping))
		{
			
			System.out.println("No need to sync TableMapping.");
		}
		else
		{
			vnTemplate.dropCollection("tableMapping");
			Iterator<TableMapping> iterator = pollTableMapping.iterator();
			while(iterator.hasNext()){
				vnTemplate.save(iterator.next());
			}
			cachedTableMapping = vnTemplate.findAll(TableMapping.class);
			System.out.println("TableMapping synced.");
			//System.out.println("NMS DB: "+cachedTableMapping.toString());
			
			
		}
		
		if(cachedSwitchMapping.equals(pollSwitchMapping))
		{
			
			System.out.println("No need to sync SwitchMapping.");
		}
		else
		{
			vnTemplate.dropCollection("switchMapping");
			Iterator<SwitchMapping> iterator = pollSwitchMapping.iterator();
			while(iterator.hasNext()){
				vnTemplate.save(iterator.next());
			}
			cachedSwitchMapping = vnTemplate.findAll(SwitchMapping.class);
			System.out.println("SwitchMapping synced.");
			//System.out.println("NMS DB: "+cachedTableMapping.toString());
			
			
		}
		
		if(cachedFlowSpace.equals(pollFlowSpace))
		{
			
			System.out.println("No need to sync FlowSpace.");
		}
		else
		{
			vnTemplate.dropCollection("flowSpace");
			Iterator<FlowSpace> iterator = pollFlowSpace.iterator();
			while(iterator.hasNext()){
				vnTemplate.save(iterator.next());
			}
			cachedFlowSpace = vnTemplate.findAll(FlowSpace.class);
			System.out.println("FlowSpace synced.");
			//System.out.println("NMS DB: "+cachedTableMapping.toString());
			
			
		}	
	}
	
	@Scheduled(fixedDelay = 5000)
	protected void vnSync(){
	
		try {
			pollVn = vnTemplate.findAll(Vn.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(cachedVn.equals(pollVn))
		{
			
			System.out.println("No need to sync Vn.");
		}
		else
		{
			nmsTemplate.dropCollection("link");
	
			Iterator<Vn> iterator = pollVn.iterator();
			while(iterator.hasNext()){
				nmsTemplate.save(iterator.next());
			}
				
				
			nmsTemplate.save(pollVn.iterator().next());
			cachedVn = nmsTemplate.findAll(Vn.class);
			System.out.println("Vn synced.");
			System.out.println("NMS DB: "+cachedVn.toString());
			
			
		}
		
	}
	
	
	
	
	@Scheduled(fixedDelay = 5000)
	public void topoSync(){
	
	try {
		pollTopo = sourceTemplate.findAll(Topo.class);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if(cachedTopo.equals(pollTopo))
	{
		
		System.out.println("No need to sync Topo.");
	}
	else
	{
		nmsTemplate.dropCollection("topo");
		nmsTemplate.save(pollTopo.iterator().next());
		cachedTopo = nmsTemplate.findAll(Topo.class);
		System.out.println("Topo synced.");
		System.out.println("NMS DB: "+cachedTopo.toString());
	
		
	}
		
	}
	
	public static void main(String[] args) {

		SpringApplication.run(MongosyncApplication.class, args);
	
	}
	public void run(String... args) throws Exception {
		
		//nmsTemplate.dropCollection(Cluster.class);
		//nmsTemplate.dropCollection(Vmsetting.class);
		nmsTemplate.dropCollection(DpInventory.class);
		
		System.out.println("Syncronizing DB...");
		
		
	}
	

}
