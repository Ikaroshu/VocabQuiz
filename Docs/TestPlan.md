# **Test Plan**

**#1 Introduction:**

This document explains the test plan for Team 33 for Group Project 2 of the Fall 2018 Semester of CS-6300. The project is for the design, development, and testing of Quiz  Application for the Android platform that allows students to practice quizzes created by each other. 

**##2 Testing Strategy:**

 2.1 Overall Strategy: The aim of the test plan is to test the functional and technical features of the app and deliver an error free, efficient app that is easy to maintain. Functional and Technical testing will focus on verifying that the different features of the app conforms to the requirements and performs as expected. Testing will be done iteratively by performing repeated validation, verification, bug fixing/code updates and re testing. 
****
**###2.2 Test Selection**: The testing process for testing the student quiz application will be broken into 3, such as:
1.	Unit Testing
2.	System Testing
3.	Stress Testing

Unit testing indicates testing performed on different individual app components to ensure that all requirements are met and the component functions normally as expected for a wide range of inputs/selections.

System testing indicates testing performed on the application, as a whole. User interface will be evaluated by executing a series of tests. This test will evaluate the response of the entry of variety of different inputs. The application will be tested to correctly handle any type of input. 

Stress test will be performed to test if the app is stable and reliable when encountering unexpected data or key inputs.

Acceptance and Regression testing of the application indicates successful execution of both system and stress test cases. 

**###2.3 Adequacy Criterion:** Tests will be performed to ensure that the app conforms to application requirements and does not contain any unwanted/inefficient features that are not within the scope of development and might hinder performance. Tests will be performed to determine positive and negative responses to ensure that the system is working as expected but also test how the system behaves when there are unexpected input or results. Unit testing will be performed to test the different individual features of the system and system testing will be done to test the integrity of the system as a whole end to end quiz app. Stress test will be performed to test if the app is stable and reliable when encountering unexpected data or key inputs.

**###2.4 Bug Tracking:**  Any defects identified during testing will be logged in github repository. Github will be our version control repository for the entire app life cycle.

Bugs identified 10/12/2018 - Quiz description not displaying correctly.

Bugs identified 10/12/2018 - score persistence between several runs and adding up to statistics.


Bugs identified 10/12/2018 - student cannot pick an existing userid - add validations


Bugs identified 10/12/2018 - when a student removes quiz scores should get removed too.


Bugs identified 10/12/2018 - student can create any user name. Add valiations.


Bugs identified 10/12/2018 - student should not remove quiz not created by student. Add validations.


**###2.5 Technology:** Unit testing of the Student quiz application will be performed by the development team and will use random test cases. Once unit testing has been completed, system test will be done by the testing team members.

We will use the Android development platform, Java and emulators available within the SDK for testing this app.  

**###3 Test Cases**

###3.1 Android Testing

| Test No 	| Scenario                  	| Description                                                                                                                      	| Test Criteria                                                                                                                                                                                                                  	| Pass/Fail 	| Comments/Defect Tracker 	|
|---------	|---------------------------	|----------------------------------------------------------------------------------------------------------------------------------	|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|-----------	|-------------------------	|
| 1       	| Student Registration      	| Add new student details and student id to the system.                                                                            	| Verify that student provided details have been submitted as input in the Student data instance.                                                                                                                                	|  Pass         	|                         	|
| 2       	| Student Registration      	| Student picks an existing Student id                                                                                             	| Verify that the System should alert the Student to pick another Student id. Student should not be able to re use or submit an existing Student id.                                                                             	|  Pass         	|   Add Alert             	|
| 3       	| Student Registration      	| Student name validation                                                                                                          	| Verify that Student name should follow standards and meet the requirements before being saved                                                                                                                                  	|  Pass         	|  Add validations    	|
| 4       	| Student Login             	| Student name Authentication                                                                                                      	| Verify successful login and load of home page menu.                                                                                                                                                                            	|  Pass         	|                         	|
| 5       	| Student Login             	| Student name Authentication                                                                                                      	| Unsuccessful login error out and redirect back to the login page.                                                                                                                                                              	|  Pass         	|                         	|
| 6       	| Menu                      	| Student can choose one of the options to MaintainQuiz, PracticeQuiz, ManageResults or MaintainStatistics.                        	| Verify that each of the corresponding pages display correctly upon selection.                                                                                                                                                  	|  Pass         	|                         	|
| 7       	| Menu - MaintainQuiz       	| Add a new quiz                                                                                                                   	| Verify that Student should be able to add a new quiz. Verify that the quiz is stored in the backend and is available for Student when accessing the practice quiz module.                                                      	|  Pass         	|                         	|
| 8       	| Menu - MaintainQuiz       	| Remove an existing quiz                                                                                                          	| Verify that Student should be able to remove a quiz that they have added.                                                                                                                                                      	|  Pass         	|                         	|
| 9       	| Menu - MaintainQuiz       	| Remove an existing quiz                                                                                                          	| Verify that Student should not be able to remove a quiz that was not created by him.                                                                                                                                           	|  Pass         	|  Add Validations         	|
| 10      	| Menu - MaintainQuiz       	| Remove an existing quiz                                                                                                          	| Verify that removing a quiz should also remove the stats attached to the quiz.                                                                                                                                                 	|  Pass         	|  Add Dependency         	|
| 11      	| Menu - PracticeQuiz       	| Student should be able to view only the quizzes that were loaded by other students for practice.                                 	| Verify that a Student is able to practice only on quizzes prepared by other students and never his own.                                                                                                                        	|  Pass         	|                         	|
| 12      	| Menu - PracticeQuiz       	| Student should be able to open the quiz question page on clicking on selecting a quiz for practice from the list of quizzes.     	| Verify that the correct quiz word page loads correctly.                                                                                                                                                                        	|  Pass         	|                         	|
| 13      	| Menu - PracticeQuiz       	| Student opens a particular quiz                                                                                                  	| Verify that words and their definitions can be viewed by the student.                                                                                                                                                          	|  Pass         	|                         	|
| 14      	| Menu - PracticeQuiz       	| Student opens a particular quiz                                                                                                  	| Verify that each word displayed has one correct definition displayed and 3 random incorrect definitions displayed.                                                                                                             	|  Pass         	|                         	|
| 15      	| Menu � PracticeQuiz       	| Student chooses a definition and submits selection while practicing a particular quiz.                                           	| Verify that the correct definition is validated and Score is updated accurately. If an incorrect definition was selected, then the Score will not be updated.                                                                  	|  Pass         	|                         	|
| 16      	| Menu � PracticeQuiz       	| Student completes a quiz                                                                                                         	| Verify that the scores have been updated correctly for all correct definitions and a final score is calculated by adding up the scores for the correct definitions selected and scores are written into the database.          	|  Pass        	|  Validate scores            	| Pass
| 17      	| Menu - ManageResults      	| Student should be able to view results for the different quizzes that were practiced by him                                      	| Verify that accurate scores for all the quizzes practiced by the student are shown when a student clicks on Manage results.                                                                                                    	| Pass          	|                         	|
| 18      	| Menu - MaintainStatistics 	| Student should be able to view overall statistical data for the different quizzes that he practiced                              	| Verify that the student is able to view different statistical data for their practice sessions like Totalscore by student in different quizzes, Individual score by student in different quizzes/                              	| Pass          	|                         	|
| 19      	| Menu � MaintainStatistics 	| Student should be able to view overall statistical data for the different quizzes that were practiced by other students as well. 	| Verify that the student is able to view different statistical data for all practice sessions like the first score for a particular quiz, the highest score for a particular quiz, Names of Top3 scorers for a particular quiz. 	|  Pass         	|                         	| Pass
| 20      	| Menu � MaintainStatistics 	| Student should be able to view the list of all quizzes.                                                                          	| Verify that the student is able to view the list of quizzes created by the student as well as quizzes practiced by the student.                                                                                                	| Pass          	|                         	|
