CREATE TABLE EVENT(
                      EVENT_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                      EVENT_TYPE VARCHAR(25) NOT NULL ,
                      EVENT_TITLE VARCHAR(45) NOT NULL ,
                      EVENT_ORGANIZER CHAR(25) NOT NULL ,
                      EVENT_LOCATION VARCHAR(25) NOT NULL ,
                      EVENT_START DATE,
                      EVENTEND DATE,
                      EVENTSTART_TIME DATETIME NOT NULL ,
                      EVENTEND_TIME DATETIME NOT NULL ,
                      DISPLAY_STARTTIME BOOLEAN NOT NULL ,
                      SINGLE_EVENT BOOLEAN NOT NULL
);

CREATE TABLE EVENT_TYPE (
                            event_typeId integer not null primary key AUTO_INCREMENT,
                            appearance_singing  VARCHAR(45) NOT NULL,
                            attraction	 VARCHAR(45) NOT NULL,
                            class_training_workshop VARCHAR(45) NOT NULL,
                            camp_trip_retreat VARCHAR(45)  NOT NULL,
                            concert_performance VARCHAR(45)  NOT NULL,
                            conference	 VARCHAR(45) NOT NULL,
                            convention	 VARCHAR(45) NOT NULL,
                            dinner_gala	 VARCHAR(45) NOT NULL,
                            festival_fair	 VARCHAR(45) NOT NULL,
                            game_competition	 VARCHAR(45)  NOT NULL,
                            meeting_networking_Event	 VARCHAR(45) NOT NULL,
                            other	 VARCHAR(45) NOT NULL,
                            party_social_gathering	 VARCHAR(45) NOT NULL,
                            race_endurance_Event		 VARCHAR(45) NOT NULL,
                            rally	 VARCHAR(45)  NOT NULL,
                            screening	 VARCHAR(45) NOT NULL,
                            seminar_talk	 VARCHAR(45)  NOT NULL,
                            tour	 VARCHAR(45)  NOT NULL,
                            tournament	 VARCHAR(45) NOT NULL,
                            tradeShow_conusmerShow_expo	 VARCHAR(45)    NOT NULL
);

CREATE TABLE USER(
                     ID BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                     FIRSTNAME VARCHAR(64),
                     LASTNAME VARCHAR(64),
                     EMAIL VARCHAR(64) UNIQUE ,
                     ROLE VARCHAR(25)
);