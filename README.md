# ExcelReader
An application that reads from an excel file and transfers certain data into another excel file. This project was inspired by my mother. For work she has to read an excel file, add up certain information and retype it out on a new excel file. To help her I created this piece of software which can carry out this task for her.
It was my first time working with excel forms. Weirdly enough I found the process enjoyable. 
To read and write the excel files I used the apache poi dependencies. 
It works by looping through the excel file from a set point and saving each cell to the database. The information in the database is then used to generate a brand new excel file  with the desired information. 

The biggest hurdle I encountered was trying to save the information to the database as a numeric value and not a String. With the apache library I could not find a way to do this. I reached the end of my self emposed deadline and decided to move on to a new project to expand my coding capabilities. If anyone is reading this I hope you have a wonderful day :).


Built With:
Spring Boot
Postgres


