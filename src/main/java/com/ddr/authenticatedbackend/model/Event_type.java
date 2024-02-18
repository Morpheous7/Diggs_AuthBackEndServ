package com.ddr.authenticatedbackend.model;


import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Event_type")
@Transactional
@Getter
@Setter
public class Event_type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_typeId")
    private Long event_typeId;

   //@OneToOne(fetch = FetchType.LAZY)
  // @ManyToOne(fetch = FetchType.LAZY/*, mappedBy = "event_typeId"*/)
//@JoinTable(name = "Event", joinColumns =@JoinColumn(name = "event_type",referencedColumnName = "event_typed"), inverseJoinColumns = @JoinColumn(name = "event_type"))
    @Column(length = 255)
    public String event_type;

    public Event_type(String event_type) {
        this.event_typeId = null;
        this.event_type = event_type;
    }

    @Override
    public String toString() {
        return "Event_type{" +
                "event_typeId=" + event_typeId +
                ", event_type='" + event_type + '\'' +
                '}';
    }
}
