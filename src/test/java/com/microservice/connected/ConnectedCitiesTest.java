package com.microservice.connected;

import org.springframework.web.client.RestTemplate;

public class ConnectedCitiesTest {
	
	public static final String REST_SERVICE_URI = "http://localhost:8080/connected?";
	      
	
	public static void main(String args[]){
		citiesNotConnectedTest();	
		citiesConnectedTest1();
		citiesConnectedTest2();
		citiesConnectedTest3();
	 }
	    /* Cities not connected test case*/
	    private static void citiesNotConnectedTest(){
	        System.out.println("Test Case 1 - REST API Call Starting ... ");
	          
	        RestTemplate restTemplate = new RestTemplate();
	       String response = restTemplate.getForObject(REST_SERVICE_URI+"origin=Philadelphia&destination=Albany", String.class);
	          
	        if(response!=null){
	                System.out.println("Service Response= "+response);
	        }else{
	            System.out.println("Service returned nothing");
	        }
	    }
	    
	    /*  Cities connected test case 2*/
	    private static void citiesConnectedTest1(){
	        System.out.println("Test Case 2 - REST API Call Starting ... ");
	          
	        RestTemplate restTemplate = new RestTemplate();
	       String response = restTemplate.getForObject(REST_SERVICE_URI+"origin=Boston&destination=Philadelphia", String.class);
	          
	        if(response!=null){
	                System.out.println("Service Response= "+response);
	        }else{
	            System.out.println("Service returned nothing");
	        }
	    }
	    
	    /*  Cities connected test case 3*/
	    private static void citiesConnectedTest2(){
	        System.out.println("Test Case 3 - REST API Call Starting ... ");
	          
	        RestTemplate restTemplate = new RestTemplate();
	       String response = restTemplate.getForObject(REST_SERVICE_URI+"origin=Boston&destination=Philadelphia", String.class);
	          
	        if(response!=null){
	                System.out.println("Service Response= "+response);
	        }else{
	            System.out.println("Service returned nothing");
	        }
	    }
	    
	    /*  Cities connected test case 4*/
	    private static void citiesConnectedTest3(){
	        System.out.println("Test Case 4 - REST API Call Starting ... ");
	          
	        RestTemplate restTemplate = new RestTemplate();
	       String response = restTemplate.getForObject(REST_SERVICE_URI+"origin=Dallas&destination=San Antonio", String.class);
	          
	        if(response!=null){
	                System.out.println("Service Response= "+response);
	        }else{
	            System.out.println("Service returned nothing");
	        }
	    }
	      
}
