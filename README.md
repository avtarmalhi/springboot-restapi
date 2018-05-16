Program in Java called "connectedCities" which determines if two cities are connected.
Two cities are considered connected if there’s a series of roads that can be travelled from one city to another.
 
List of roads is available in a text file named citiesList.txt.
File contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.

File content:
Boston, New York
Philadelphia, Newark
Newark, Boston
Trenton, Albany
Dallas, Houston
Austin, Dallas
Dallas, San Antonio
Orlando, Miami

 
In this program, cities as considered as nodes in a graph and each city pairs in the files are considered an edge between two nodes (cities). 
For example, Boston and Philadelphia are connected through Newark as we can see in the file, Philadelphia and Newark are connected and Newark is connected to Boston. In short, once can reach Philadelphia from Boston through Newark. On contrary, Dallas is not connected to Miami as there is no road connection between them.
 
This program is deployed as spring-boot app exposed through Rest API. Service returns response as "Yes" or "No"
 
Service Endpoints Tests:
  
http://localhost:8080/connected?origin=Boston&destination=Newark
Response: Yes 

http://localhost:8080/connected?origin=Boston&destination=Philadelphia
Response: Yes 

http://localhost:8080/connected?origin=Philadelphia&destination=Albany
Response: No 

http://localhost:8080/connected?origin=Sacramento&destination=San Diego
Response: No 

http://localhost:8080/connected?origin=Dallas&destination=Miami
Response: No 

Any unexpected input returns result as ’No’ in the service response.

Test case are added in the "ConnectedCitiesTest" class which evaluates all the valid scenarios.
 
Remarks
 
1. Assuming a specific size "citiesList" file which can fit into memory to process data, If larger size file is used in this program, program can become inefficient. We can go use other data structures or algorithms which can handle large file data and make program more efficient. 