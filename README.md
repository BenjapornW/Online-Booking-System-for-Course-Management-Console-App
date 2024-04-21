S3961136 - My Online Booking System for Course Management

------
[updated 10 April 2023]
========
#   How to compile and run java program from command line
1) Download the program
2) Open the terminal and change the directory to the folder that you unzip the console program
3) Compile the program using javac:


>javac ./src/org/example/*.java

4) Run the program using java:

>java -cp ./src org.example.Main


# How to run Junit tests using IntelliJ:
- run test directly from the class

# How to run Junit test using Eclipse:
1) Import project
- Import > Project from Folder or Archive > Choose the folder
2) Add Junit4 Library
- Build Path > Add Library > JUnit > JUnit4

#   8-min Demo Video

Please go to the below link:

[Assignment1_Demo](https://rmiteduau-my.sharepoint.com/:v:/r/personal/s3961136_student_rmit_edu_au/Documents/Recording-20230411_101453.webm?csf=1&web=1&e=EFdRkS)


-----------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------

#	Clearly describe your class design (i.e., classes and significant methods).

There are 5 classes in my program:

Main class, Timetable class, Course class, CourseRecord class, ManageEnrolment class

1.	Main class: the main method is here and we run the program from here. Also, the objects are created here.

2.	Timetable class:

    2.1)	run () method: when the program starts it will read the course list from the csv file first and then run the mainMenu () method
    2.2)	mainMenu () method: main task of the program. This method run by calling method from mangeEnrolment in order to manipulate enrolment list.
    2.3)	readUserInput () method: call scanner in order for user to insert the word
    2.4)	printMenu () method: print main menu choices


3.	Course class: like a blueprint to create course object. When we create the object, the object will have all this attributes. Also, since our attributes are encapsulated (you can see that it’s private variables). The getter methods are created because I want to print them later.


4.	CourseRecord class:

    There are 2 methods accordingly:
    4.1) read_course() method : use buffer to read .csv file and separate each value for future use
    4.2) search_course() method: search if the keyword match with the name in the course list

5.	ManageEnrolment class:

    There are 3 methods accordingly:
    5.1) deleteCourse() method
    5.2) displayEnrolment() method
    5.3) enrolSubject() method

-----------------------------------------------------------------------------------------------------
#	Clearly explain your choice of the data structures in JCF.

I chose ArrayList because
1)	I am familiar with Array. So, it’s easy for me to understand, access, add, delete data from the ArrayList.
2)	It’s a resizeable array that I don’t have to worry about the length.
3)	Element of ArrayList can be accessed randomly. In this case, I will ask user to insert the number (Which is random because we don’t know what the subject the user wants to enroll. Then, I will use that number to get the object in the ArrayList by index.

-----------------------------------------------------------------------------------------------------
#   3-min Demo Video

Please go to the below link:

[Milestone_Demo_Week4](https://rmiteduau-my.sharepoint.com/:v:/r/personal/s3961136_student_rmit_edu_au/Documents/Recording-20230324_001002.webm?csf=1&web=1&e=cMQwPn)
