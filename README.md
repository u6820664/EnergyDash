Table of Contents
=================
- [Background](#Background)

- [Tools and Skills Related](#Tools_and_Skills_Related)

- [Requirement](#Requirement)

- [Stakeholder Analysis](#Stakeholder_Analysis)

- [Identification of Resources, Risks, Potential Costs](#Identification_of_Resources_Risks_Potential_Costs)

- [Role and Responsibility](#Role_and_Responsibility)

- [Schedule and Due Date](#Schedule_and_Due_Date)

- [Documentation](#Documentation)

- [Appendix](#Appendix)

  

# <span id="Background">1 Background<span>

## 1.1 Problem

In recent years, climate change has become a global issue affecting human life.


However, when it comes to protecting the environment, many government actions always lag more than 20 years, making the response to climate change extremely lagging. In addition, in the face of the delayed improvement of the energy structure, ordinary energy users will still face high bills. Currently, in Australia, the coal-based power grid is expected to be phased out around 2040, and it is difficult to completely replace coal with renewable energy.


How to optimize Australia's energy structure and provide cheap and sustainable energy for energy consumers has become a great challenge for the Australian energy industry.


## 1.2 Solution

To address the current dilemma facing the Australian energy industry, we have proposed a solution that does not affect the interests of all parties.


First, we obtained the energy plan content of most mainstream energy providers in Australia through the data crawler tool, including the data of energy application type, energy price, energy plan period and so on.


Second, we will establish a set of energy program recommendation system, users only need to input the basic information (such as postal code, energy habits, etc), through the data processing unit and recommendation algorithm, we will according to the situation of the users, for their match will be the most suitable energy use plans, and inform the user to use or change an energy plan, it will reduce on energy use for the users of your bills.


Finally, through the real-time data display platform, the system adopts a variety of access methods, so that users will get real-time suggestions on energy use and inform energy users in a simple and understandable way whether it is a reasonable time to use energy before that time.


# <span id="Tools_and_Skills_Related">2 Tools and Skills Related<span>

## 2.1 Project Management Tools

- [Slack](https://app.slack.com/client/T018HVDJHGR/C018HVDJQMP) (General Q&A)
- [Monday](https://rexergy-company.monday.com/boards/1084297759/) (Task management)
- [Github](https://github.com/u6820664/EnergyDash) (Code branch)
- [Google Drive](https://drive.google.com/drive/folders/1ZiKnLC1JR_i2kxBUCV4yKgBujJBVFTC4?usp=sharing) (Document)
- Zoom (Meeting)



## 2.2 Development Tools

### 2.2.1 Programming Language

Java and most likely a combination of JavaScript and Python.



### 2.2.2 Testing

- Unit test during development by black/white box.

- A/B test for the final stage.



# <span id="Requirement">3 Requirement<span>

## 3.1 Product Functions

This project creates a real-time data dashboard for energy users, predicts and reminds consumers of the best time to use energy through the analysis of consumers' energy solutions and locations. The main purpose is to develop a more rational energy use strategy for consumers, while indirectly reducing the burning of coal and other fossil fuels.
The project will be implemented on multiple platforms, including Web App, Mobile(Android) and Chrome Extension.


- Basic functions
  - Implement sign up/in via email and password
  - Implement display single number metric in real-time
  - Implement swap between the main metrics


- Main functions
  - Implement input user Information (Energy Bill Prices and Times, Postcode)
  - Implement edit user Information in Settings
  - Implement display history of metrics in line graph (day, week, months, years)
  - Implement display cumulative metrics
  - Implement create energy report
  - Compare performance against expected, or average user
  - Display savings in the metrics
  - Implement create plan for user Data Security and Storage for back- end
  - Implement turn metrics into a colour, and incorporate into background
  - Realize savings on energy bills by providing users with recommended plans and using or changing energy plans
  - The implementation generates forecast data through the analysis of historical data
  - Implement display prediction index




## 3.2 General Constraints

- The back-end of the program will be based on Java
- The web-based front-end should utilize HTML, CSS and JavaScript
- The program can process the data from clients’ company
- The input data includes pure energy usage data



## 3.3 Specific Requirements

### 3.3.1 Identification Methods

- The model used should be able to recommend reasonable energy usage status prompts to users in real time through the calculation of energy indicators assigned weights.
- The program should be able to recommend more reasonable energy use options to users based on metric and location.
- The program may provide customized energy use solutions based on user expectations.



### 3.3.2 System Output

- The program should display the prompt of energy use status and scores of each evaluation index in the front end.
- The program needs to refresh the existing user interface to achieve real-time performance.



### 3.3.3 System Input

- The program should be able to process energy data collected from smart devices.

- The model developed should use the existing energy data set of news as the training set provided by the client.



## 3.4 Minimum Viable Product (MVP) 

### 3.4.1 An Operational Website

- The program should at least implement.
- The requirement in 3.3.2 System Output to calculate and display energy usage tips for input energy data in a simpler and clearer way, and list them at the front-end.
- The requirements in 3.3.3 System Input, be able to receive the latest energy data.



### 3.4.2 Appropriate Algorithms in Data Analysis

Our team is committed to applying and improving some of the current open source data analysis algorithms.



### 3.4.3 User Survey Report

User surveys play an important role in both front-end and back-end development and how algorithms work. For this project, our goal was to get feedback on different types of households, understanding the level of energy consumption in each household and the proportion of energy bills in each household, which would help us design the project and improve the user experience on the site.



# <span id="Stakeholder_Analysis">4 Stakeholder Analysis<span>

## 4.1 Client’s Vision and Objectives 

The client's vision is to inspire people to respond to climate change, by choosing more environmentally friendly energy, while helping people save more money that should have gone into the pockets of coal plants.

## 4.2 Key Stakeholders

- Client: Ben Wilkinson, Rexergy’s CEO.
- Development Group Members: A group who is responsible for planning and executing the project.
- Shadow Team: Our peers, who will assess and provide feedback for our deliverables.
- Tutors: They are professionals who help our group to understand the aim of the project, assist and  guide students in the learning process.
- Other Examiners: People who assess our deliverables of each stage.
- Energy Consumers: One of the main users of our website, who wants to enjoy their services on our    website.Including households and factories.

## 4.3 Stakeholders' Expectation

- Client: expects the project outcome to fulfill users’ needs, be fully functional and be user friendly.
- Development Group Members: expect the project to be managed under professional manner and provide outstanding outcome through the project.
- Shadow Team: expects to see an on-track and well-management project from all the resources provided by our team.
- Tutors: expect to see weekly progress from our team and professionalism from all the members in our team.
- Other Examiners: expect to see a project outstanding from our team according to the marking criteria.
- Energy Consumers: expect to obtain useful insights from the project outcome.



## 4.4 How Will This Project Make Things Better?

- Create an application that enables people to have a better impact on protecting the environment.
- Let people know if it is a good time to use more, or less power.
- Power of collective action can focus impacts and create faster change.
- Give them feedback on their actions, impacts.
- A simple application that can provide services to every member of the family, including children and the elderly.



# <span id="Identification_of_Resources_Risks_Potential_Costs">5 Identification of Resources, Risks, Potential Costs<span>

## 5.1 Resources

- Team members’ laptops used for data processing
- Team members’ laptops used for software developing
- The web development frames
- Dataset about different types of energy use



## 5.2 Risks

- The risk of failing to achieve the expected accuracy and efficiency
- The risk of failure to final integrate due to the disunity of work division
- The risk of timeouts which leads to the failure of debugging and final deliverable
- The risk of failing to respond to the feedback from the user survey



## 5.3 Risks Management

- Review work every 2 weeks. If any process is falling behind, try switch methods/ add additional workload/ adjust team structure

- Conduct First user survey early (in week 5), so the team will have sufficient time to deal with user feedback
- Communicate with stakeholders weekly



## 5.4 Potential Costs

- Budget for entity legal documentation and licenses. The client will bear it.
- Budget for purchasing web development frames. The client will bear it.
- Time used for the whole project. All team members will bear it.
- The commuting and depreciation fee for meeting and development. All team members will bear it.



# <span id="Role_and_Responsibility">6 Role and Responsibility<span>

| Name           | Uni ID   | Role                                   |
| -------------- | -------- | -------------------------------------- |
| Qinzhuo Li     | u6808341 | Back-End Developer,  Spokesperson      |
| Alex  Xiu      | u6820664 | Front-End Developer,  Spokesperson     |
| Haoyu  Wang       | u6783702 | Back-End Developer,  Database designer |
| Jiaoyang Sun    | u7075346 | Data Analyst             |
| Zixin  Ye      | u6605822 | Front-End Developer,  Documentation    |
| Mingchao  Sima | u6502811 | Back-End Developer,  Algorithm designer|
| Jie  Zou       | u6849083 | Front-End Developer                    |

 

# <span id="Schedule_and_Due_Date">7 Schedule and Due Date<span>

## 7.1 Schedule

| Time              | Plan                                                         |
| ----------------- | ------------------------------------------------------------ |
| Week  01          | ●      Form team                                             |
| Week  02          | ●      Divide roles<br/>●      Confirm requirements with clients<br/>●      Decide schedule and milestones<br/>●      Risk management |
| Week  03          | ●      Project Audit 01<br/>●      Finished legal issues<br/>●      Set up the development environment<br/>●      Improve and finalise the SOW<br/>●      Search for related information |
| Week  04          | ●      Design web UI<br/>●      Re-Design Database structure (with E-R)<br/>●      Re-Design Mockup<br/>●      Start coding |
| Week  05          | ●      Improve website functions<br/>●      Improve data analysis algorithm |
| Week  06          | ●      Audit 02<br/>●      Coding                            |
| Teaching  Break-1 | ●      Coding                                                |
| Teaching  Break-2 | ●      Coding                                                |
| Week  07          | ●      Compared among different data analysis algorithm<br/>●      Test & Debug<br/>●      Adjusting UI and functions according to reflection<br/>●      Conduct the user feedback |
| Week  08          | ●      Conduct the second user survey<br/>●      Analyse and reflect to survey report<br/>●      Keep improving UI interface according to reflection<br/>●      Keep improving the data analysis algorithms according to reflection |
| Week  09-10       | ●      Audit 03<br/>●      Accomplish final deliverable<br/>●      Re-Design Project Video<br/>●      Showcase<br/>●      Adjusting UI and functions according to reflection |
| Week  11-12       | ●      Finish handover documents<br/>●      Report to the client |

**Gantt Chart**

# ![Diagram](Resources/Gantt_Chart.png)

## 7.2 Due Date

The client does not propose any due day so we will follow the due day of this course to arrange our project (https://cs.anu.edu.au/TechLauncher/dates/)



# <span id="Documentation">8 Documentation<span>


## 8.1 Project Agreement

### [Statement of Work](https://drive.google.com/file/d/12tLfls5pkxizoJvyAn6dVs2xAEdaUKTn/view?usp=sharing)

### [Non-Disclosure Agreement and any Intellectual Property](https://drive.google.com/file/d/1Dzu0JycLEu9UdVrfnF9sfhLhG3bl20q_/view?usp=sharing)

### [Team Charter](https://drive.google.com/file/d/1pmdKD5qTtafa2FyvKnvyHnLsv1tPk_1U/view?usp=sharing)


## 8.2 Reasoning

### [Decision Making Log](https://drive.google.com/file/d/1OYYqmoGFWluf5sC83kGeEFqLps5CMbGK/view?usp=sharing)

### [Risk Assessment](https://drive.google.com/file/d/1VwItzxZ4HXE-6Q8GRQW2awXppRpGOXd1/view?usp=sharing)



## 8.3 Feedback&Reflection

### [Feedback&Reflection](https://drive.google.com/drive/folders/1mOen_1rinE8JD-2ItWBkpfDgqPUh5ejf?usp=sharing)



## 8.4 Meeting Agenda

### [Client Meeting](https://drive.google.com/drive/folders/1b4LXe25A1e8mvnNACgBO3Nm5nXbNxVnq?usp=sharing)

### [Group Meeting](https://drive.google.com/drive/folders/14iQ9F7pz4-8Ze4D39rwnnjU4TOkds3Yg?usp=sharing)

### [Tutorial Meeting](https://drive.google.com/drive/folders/1WYyuf1Pzv8t-DotNMji8u5gJn_cIw7qJ?usp=sharing)



## 8.5 Back-End

### [Database Design](https://drive.google.com/drive/folders/19knAw8IHXNzad8U5dhfQt4VRO404zVnY?usp=sharing)

### [UML](https://drive.google.com/drive/folders/12qYF1f2b75q0imdozGkLMq2D9bHybGEK?usp=sharing)

### [Algorithm Design](https://drive.google.com/drive/folders/1yEpM7vOa2Jbf0M3TQW7-tPS9HmNimz-7?usp=sharing)



## 8.6 Front-End

### [UI Design](https://drive.google.com/drive/folders/1eNAf-4_XLgdgPVrnK51_wAnGaOZuuUOg?usp=sharing)


## 8.7 Testing

### [User Tesing](https://drive.google.com/drive/folders/1ldUD0WwO62g096d5iLbHCLbNyLDWULwR?usp=sharing)

### [API Tesing](https://drive.google.com/file/d/1UaetZZt32kuJsnC40Vky1LiaZsCtqCHT/view?usp=sharing)


## 8.8 Other Resources

### [Research Sharing](https://drive.google.com/file/d/1k4EdyBkzy9MZpOHKO2dmYDs8qiJ1dWxc/view?usp=sharing)

### [API Documentation](https://drive.google.com/file/d/1frD5Pkk1CbKgRKCslzcWi9O8TIbYaXQq/view?usp=sharing)

### [Stakeholder Analysis](https://drive.google.com/file/d/1FjCHKAHK1PCBThPKEvvTgVUUah_7_ce8/view?usp=sharing)

### [Sprint Retrospective](https://drive.google.com/drive/folders/1Q38OSdE6j1ROt6hZbTPdrxuM6VVB_LFY?usp=sharing)

### [All Documentation](https://drive.google.com/drive/folders/1ZiKnLC1JR_i2kxBUCV4yKgBujJBVFTC4?usp=sharing)


# <span id="Appendix">Appendix<span>

## User Story Map

![Diagram](Resources/User_Story_Map.png)



## Roadmap

![Diagram](Resources/Roadmap_2021-S1.png)



## Milestones

![Diagram](Resources/Milestone.png)



## Architecture Diagram

![Diagram](Resources/Architecture_diagram.png)


## Project Video

### [Project Video](https://youtu.be/te2DoCKfq8g)


## Dev Environment (for Test only)

Please open these page via Firefox browser.


### [Sign in Page](http://54.79.60.225/pages/auth/login.html)

### [Sign up Page](http://54.79.60.225/pages/auth/register.html)

### [User Profile Page](http://54.79.60.225/pages/users/profile.html)

### [Dashboard Page](http://54.79.60.225/dashboard.html)


