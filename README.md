# Team2_Repository
Gymnastics Scoreboard

Rationale

Women’s College gymnastics in the Southeastern Conference is highly competitive and well attended. To help with the fan’s experience in the arena a scoreboard showing the gymnast’s and team’s current status is important. 

This is a four (4) team project. 

Features
The features listed below shall be included in the software.
1.	The Scoreboard shall support the Dual, Triangular and Quadrangular Meet Formats. 
2.	The Scoreboard shall have a set-up mode that allows the user to customize the Scoreboard (arena screen) prior to the start of the meet. 
a.	A default template shall be provided by the system based on the Meet Format. 
b.	The template will allow the user to insert team logos on the arena screen.
c.	Select the team line-up for each of the apparatus from a database. The user must be able to select the apparatus, gymnast name, picture and order. In the case of the vault the vault name will be selectable. Any apparatus that has a start value will also be selectable.
d.	Select the judges for each of the apparatus from a database. 
3.	The Scoreboard shall have a Check-Meet mode that will verify all apparatus events have the correct number of gymnasts, judges and identify gymnasts that are eligible for the all-round competition. 
4.	The Scoreboard shall have a run mode that is used during the meet.
a.	The run-mode shall have the arena screen and the scorekeeper screen. 
b.	Any apparatus that has a time-limit shall be displayed as a countdown clock.
c.	The scorekeeper will enter the final event scores.
d.	While the gymnast is on the apparatus the arena scoreboard shall display information about the gymnast (picture, class, major, season average score on the apparatus)
e.	The running meet score and all-around competition will be updated after the judge scores are entered for each gymnast and apparatus score. 
f.	When a score is updated for a gymnast or team the area screen shall flash to alert the fans this is a new score. 
g.	The scoreboard must allow for line-up changes during the competition. 
h.	Note the lowest team apparatus score is dropped from the team scoring. 
5.	The Scoreboard shall have a post-meet mode.
a.	Arena screen displays final meet result scores and team placement
b.	Arena screen displays all-around competition result scores and placement
c.	Save all the meet scoring.
d.	Allow the user to review the meet statistics.


There is already Gymnastics scoring software available and this project is not intended to replace that software. A selling point for the scoreboard software is a possible future enhancement to interface with the existing scoring system to update the scoreboard. Until that happens, the scoreboard keeper interface should be easy to use and allow for quick and error free updating the arena scoreboard. Error checking for data entry needs to take place (no scores over 10, negative, etc.). 

The NCAA Rules of the Game site http://www.ncaa.org/playing-rules/gymnastics-rules-game provides the latest version of the Women’s Rules that the software should support. The team should use the latest released version of the rules. 

YouTube has many examples of meets. Here are a couple that can be used as reference:
Dual meet – Alabama at LSU from 2018 : https://www.youtube.com/watch?v=T9IgAMmOtcE 
SEC Championship 2018 : https://www.youtube.com/watch?v=4x_92nXHR3A 

Additional reference for scoring and score sheets for a meet: http://gysegem.com/index.htm 

Note: Data does not have to be stored in a “database”. This is an implementation or design decision that needs to be made by the team. 


Constraints
1.	The choice of programming language and database is left up to the design team.
2.	Users must be able to run the program on a PC under Windows.
3.	Details on scoring and rules is provided by the instructor in the NCAA Women’s Gymnastics Rules Modifications and Meet Procedures and will take precedence if there is a conflict with this assignment and anything your instructor may state. 
4.	The features identified above are not comprehensive. For example, the NCAA Women’s Gymnastics Rules Modifications and Meet Procedures document requirements for scoring and meet conduct that may or may not be listed in the feature section above. 
5.	The system will be considered to be open source and in the public domain, therefore all code must be original and may not include any copyrighted material.

The acceptance test for this project should use a dual meet video and score the meet concurrent with the video. 
 
Student Feedback from previous semesters. 

The following information was provided by students at the end of the term and should be considered as lessons learned. Some of this information may or may not be useful because the project requirements may have changed from previous terms. 

Spring 2021

Our team decided to use C# for our project and also used winforms to create the GUI. My recommendation is that if you haven't had much experience with C# and your group decides to use it, set up the GUI as soon as possible. Since there are a lot of forms for the set-up, the scoreboard, and the results, the GUI needs to be completed as soon as possible. For this project in particular, I would definitely leave as much time as possible for testing. When we were testing our project, it took forever for us to get through the set-up. We were averaging about 3-5 minutes going from start to finish in our project, so just be mindful of that. We also used a SQL server for our database. If you have someone on your team that knows how to setup a SQL server (or MySQL), then I would at least consider doing that for the database.

Research the rules and the flow of gymnastics as soon as possible. Also, take screenshots of the necessary scoreboards throughout the SEC meet youtube video. Collect as many real research items, so the final product can look and function as a real scoreboard. This would help the creation of the GUI and the start of the development.

I believe Window Forms in C# with SQL database were good tools for implementing the gymnastics scoreboard. WinForms is an older software, so another framework may supply the team with better GUI functionality. However, I believe it was good enough to get the requirements completed.

1. Start the GUI early
2. Have a real understanding of the "rules" of womens gymnastics
3. Testing is tedious so start it early
Githubs connection with Visual Studio was occasionally problematic. However it did not cause any major issues.

We didn't have any serious issues with the software tools that we picked out, but we did have to spend some time learning how to use some of the tools, as well as making sure that everyone was able to access and run each tool. We used c#, winforms, and SQL to implement our project, and we didn't have any serious issues. 

Get an idea of how the scoreboard will function early so that you can split up the work and leave enough time to polish the features.

We used the github extension in Visual Studio 2017 which worked pretty well most of the time. There were a few instances though where our project versions didn't seem to sync up.
