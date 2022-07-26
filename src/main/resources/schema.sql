
CREATE TABLE IF NOT EXISTS MOVIE_TABLE (
ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
ADD_INFO VARCHAR(255), 
CATEGORY VARCHAR(255) NOT NULL, 
NOMINEE VARCHAR(255) NOT NULL, 
WON VARCHAR(3) NOT NULL DEFAULT 'NO', 
YEAR_NOMINATION VARCHAR(255) NOT NULL,
RATINGS NUMERIC(5,2) NOT NULL default 0.00,
TOTAL_VOTES INTEGER NOT NULL default 0,
BOX_OFFICE_COLLECTION DOUBLE PRECISION NOT NULL default 0.00
);

CREATE SEQUENCE IF NOT EXISTS HIBERNATE_SEQUENCE 
START WITH 1 
INCREMENT BY 1 ; 
  
  