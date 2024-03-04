package com.digs.logindemo.service;




import com.digs.logindemo.model.Event;
import com.digs.logindemo.model.SecurityUser;
import com.digs.logindemo.repository.EventDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ike Kennedy
 */

@Service
public class EventService {

    private EventDao eventDao;

    public EventService() {
    }


    public ResponseEntity<String> createEvent(Event event, SecurityUser findUser) {
        findUser.setEvent(event);

        eventDao.save(event);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<SecurityUser>> getAllEvents() {
        try {
            return new ResponseEntity<List<SecurityUser>>((MultiValueMap<String, String>) eventDao.findAll(), (HttpStatusCode) HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> getEventById(Event eventId) {
        eventDao.findById(eventId.getEvent_Id());
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<String> addEvent(Event event, SecurityUser findUser) {
        findUser.setEvent(event);
        eventDao.save(event);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteEvent(Event event, SecurityUser findUser) {
        ArrayList<Event> event1 = (ArrayList<Event>) findUser.getEvent();
        if(event1.contains(event)) {
            event1.remove(event);
            findUser.setEventList(event1);
            eventDao.deleteById(event.getEvent_Id());
        }

        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteById(Event eventId) {
        eventDao.deleteById(eventId.getEvent_Id());
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

}
