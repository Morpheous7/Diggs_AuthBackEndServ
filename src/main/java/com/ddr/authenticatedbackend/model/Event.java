package com.ddr.authenticatedbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * @author Ike Kennedy
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Transactional
@Table(name = "Event", schema = "restapi")
//@SecondaryTables({
  //      @SecondaryTable(name = "Event_type", pkJoinColumns = {@PrimaryKeyJoinColumn(name="event_typeId")/*, @PrimaryKeyJoinColumn(name="event_type")*/})
//})
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_Id")
    private Integer event_Id;

    //@ManyToOne(fetch = FetchType.LAZY)
    /*@JsonIgnore
    @MapKey(name = "event_type")*/
    @JoinTable(name = "Event_type", joinColumns = @JoinColumn(name = "event_Id",referencedColumnName = "event_typeId" ), inverseJoinColumns = @JoinColumn(name = "event_type"))
    private String event_type;
    private String event_Title;

    //    @JoinColumn(name = "event_Organizer", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
//    @MapKey(name = "name")
    @JoinTable(name = "user", joinColumns = @JoinColumn(name = "id",referencedColumnName = "event_id" ), inverseJoinColumns = @JoinColumn(name = "user"))
    private User event_Organizer;
    private String event_Location;
    private Date eventStart;
    private Date eventStart_Time;
    private Date eventEnd;
    private Date eventEnd_Time;
    private boolean display_StartTime;
    private boolean single_Event;


}
