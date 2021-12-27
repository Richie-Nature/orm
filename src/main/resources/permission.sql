CREATE USER 'sa'@'localhost' IDENTIFIED BY 'sa1234'; #create the user
GRANT ALL PRIVILEGES ON plant.* TO 'sa'@'localhost'; #Gives all privileges to the new user on plant