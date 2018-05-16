package com.microservice.connected.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.connected.service.ConnectedCities;
import com.microservice.connected.utility.CustomErrorType;

@RestController
@RequestMapping("/connected")
public class RestApiController {

@Autowired
ConnectedCities connectedService;

@RequestMapping(method = RequestMethod.GET)
public ResponseEntity<String> areCitiesConnected(@RequestParam("origin") String origin,
		@RequestParam("destination") String destination) {
    String response = ConnectedCities.areCitiesConnected(origin, destination);
    if (response == null) {
        return new ResponseEntity(new CustomErrorType("Service could not return valid result."), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<String>(response, HttpStatus.OK);
}


}
