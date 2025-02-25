
#Players Service Readme#

#########

I have used:
Java version 17,
Maven 3.9.3, 
Spring-Spring Boot framework version 3.1.4. 

#########
 
Rest Apis endpoints:
Tested on localhost:8080
In addition to file player.csv I have added for testing 
playerDemo.csv under resources directory. 
For example with playerDemo.csv: 
For rest api end point http://localhost:8080/api/players

response

[
{
" rollno": " 42",
" cgpa": " 8.6",
" department": " cse",
"name": "amar",
" result": " pass"
},
{
" rollno": " 21",
" cgpa": " 3.2",
" department": " ece",
"name": "rohini",
" result": " fail"
},
{
" rollno": " 23",
" cgpa": " 8.9",
" department": " cse",
"name": "aman",
" result": " pass"
}
]
for example for rest api end point http://localhost:8080/api/players/amar

response 

{
" rollno": " 42",
" cgpa": " 8.6",
" department": " cse",
"name": "amar",
" result": " pass"
}

##########








