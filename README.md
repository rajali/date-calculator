# Introduction
The text below is copied from the problem details sent through email:

## Date Calculator : Introduction 
There are a few constraints, however they are mainly designed to help us evaluate how you did. Unless otherwise instructed, you can use any modern language and environment to write your solution, as long as you consider: 
The solution must compile and run on a Unix based desktop/laptop, and suitable instructions how to do this should be provided. 
1. The solution should be complete and provide means to validate the proposed outcome. 
2. It should accept input and be testable from the command line. 
3. Language features/classes, (i.e. native date or calendar classes), framework classes or helper libraries should not be used to solve the problem, however they’re ok to use for testing purposes. 


## Date Calculator : Problem 

You are tasked with calculating the number of full days elapsed in between start and end dates. The first and the last day are considered partial days and never counted. Following this logic, an experiment that has run from 1972-11-07 to 1972-11-08 should return 0, because there are no fully elapsed days contained in between those dates, and 2000-01-01 to 2000-01-03 should return 1. The solution needs to cater for all valid dates between 1901-01-01 and 2999-12-31. 
Implement a command line based system with at least one way of providing input and output on the terminal. Although it should obvious, make sure the solution compiles and works. 
Although any dates specified within the valid date range listed above should work, here are a few test cases to validate the output of your program. 


### TEST CASES 
1. 1983-06-02 – 1983-06-22: 19 days 
2. 1984-07-04 – 1984-12-25: 173 days 
3. 989-01-03 – 1983-08-03: 1979 days
 
####HINTS

 1. We really do want to know how you did it. If you’re tempted using a search engine to find a solution, please be aware we know how to use search engines too.
2. Less code is more - create a concise, however a complete solution. 
3. If it looks like it’s going to take significantly longer than a few days to complete, it may be a good time to reconsider your approach. 
4. We do want you to succeed, so help us understand why you make design or programming choices – it will vastly improve the solution. 
5. Last but not least: there is no perfect implementation, so don’t try to build one. 


# Steps
- Create project structure using maven archetype as:

```
mvn archetype:generate "-DgroupId=au.com.live.rajali.date_calc" "-DartifactId=date-calculator" "-DarchetypeGroupId=com.thepracticaldeveloper" "-DarchetypeArtifactId=archetype-java-basic-tpd" "-DarchetypeVersion=1.0.0" "-DinteractiveMode=false"
```

