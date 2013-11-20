INSERT INTO GROUPS (GROUP_NAME, GROUP_DESC) 
	VALUES ('Customers', 'Bank customers');
INSERT INTO GROUPS ( GROUP_NAME, GROUP_DESC) 
	VALUES ('Employees', 'Bank Tellers');
INSERT INTO GROUPS (GROUP_NAME, GROUP_DESC) 
	VALUES ('Admins', 'Bank Administrators');

INSERT INTO PERSON (PERSON_ID, EMAIL,FIRST_NAME,LAST_NAME, USER_PASSWORD) 
	VALUES (1 , 'lars@gmail.com', 'Lars','Mortensen','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');
INSERT INTO PERSON (PERSON_ID, EMAIL,FIRST_NAME,LAST_NAME, USER_PASSWORD) 
	VALUES (2 , 'peter@gmail.com', 'Peter','Petersen','3e0a3501a65b4a7bf889c6f180cc6e35747e5aaff931cc90b760671efa09aeac');
INSERT INTO PERSON (PERSON_ID, EMAIL,FIRST_NAME,LAST_NAME, USER_PASSWORD) 
	VALUES (3 , 'thomas@gmail.com', 'Thomas','Kragsberger','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');
INSERT INTO PERSON (PERSON_ID, EMAIL,FIRST_NAME,LAST_NAME, USER_PASSWORD) 
	VALUES (4 , 'hans@gmail.com', 'Hans','Hansen','3e0a3501a65b4a7bf889c6f180cc6e35747e5aaff931cc90b760671efa09aeac');

INSERT INTO PERSON_GROUP (EMAIL,GROUP_NAME) VALUES ('lars@gmail.com','Employees');
INSERT INTO PERSON_GROUP (EMAIL,GROUP_NAME) VALUES ('peter@gmail.com','Customers');
INSERT INTO PERSON_GROUP (EMAIL,GROUP_NAME) VALUES ('thomas@gmail.com','Customers');
INSERT INTO PERSON_GROUP (EMAIL,GROUP_NAME) VALUES ('hans@gmail.com','Customers');
