<a href="https://www.freepik.com/free-vector/vector-photographer-character-camera-professional-operator-correspondent-man-illustration_10602740.htm#query=photography%20illustration&position=19&from_view=search&track=sph">
  <img src="https://img.freepik.com/free-vector/vector-photographer-character-camera-professional-operator-correspondent-man-illustration_1284-42379.jpg?w=826&t=st=1666358657~exp=1666359257~hmac=9c54bd46a506c1607f134de0130925b6aa15b323b2e57aea19a32f3380a9e9fa" align="right"
     alt="Size Limit logo by Anton Lovchikov" height="300">
</a>

# PicScore 
[![Activity](https://img.shields.io/github/commit-activity/m/Franek-Antoniak/picscore)](https://github.com/Franek-Antoniak/picscore) [![CodacyGrade](https://app.codacy.com/project/badge/Grade/a7c4e661039547eea4fe1369b3cb41ff)](https://www.codacy.com/gh/Franek-Antoniak/picscore/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Franek-Antoniak/picscore&amp;utm_campaign=Badge_Grade) [![LastCommit](https://img.shields.io/github/last-commit/Franek-Antoniak/picscore)](https://github.com/Franek-Antoniak/picscore) [![Deploy](https://img.shields.io/github/deployments/Franek-Antoniak/picscore/picscore)](https://picscore.herokuapp.com/)
> Who has the best photo? You decide!

PicScore is an application for schools, organisations, groups of friends or any people who want to make a competition between themselves for the best photo. 
<br>
It is built on a simple student-teacher architecture. Feel free to use it through my heroku instance or build the project on your own computer!

* Add your own **photos**.
* If you add more than one photo, scroll them through the **preview**. 
* Delete photos you've added by mistake.
* **Zoom** in and out on photos in the **gallery**.
* Cast **three votes** for the **best** images added by your friends!
* See live how many votes your **photos** get.
* Once the voting is over, the **teacher/administrator** will give you the **results**.
* **Restart** the application when you have finished playing!

## Table of content

- [Heroku instance](#heroku-instance)
- [Roles](#roles)
    - [User](#user)
    - [Administrator](#administrator)
- [Technological description](#technological-description)
    - [Front-end](#front-end)
    - [Back-end](#back-end)
    - [Database](#database)
    - [Deployment](#deployment)
- [Screenshots](#screenshots)


## Heroku instance
 
- If you want to try out the app you can use my instance on **[Heroku](https://picscore.herokuapp.com/)** <br>
- You only need a Google account to start using the application as user <br>
- To log in as administrator and have more permissions you need additional login details
<details> <summary>Credentials</summary>
<code> Login: admin</code> <br>
<code>Password: pass</code></details>


## Roles
### User
* Upload photos
* Delete your photos
* Vote for others' photos
* See how many votes your photos got.
### Administrator
* User capabilities
* Deletion of all data
* Preview of results

## Technological description

### Front-end
> - The front-end of the application is built using HTML, CSS and JavaScript. 
> - jQuery is used for DOM manipulation and AJAX requests to communicate with the back-end. 
> - Bootstrap is used for the responsive design of the application. 
> - I have used modals to show the images on the side of the app rather than redirecting to another page. 
> - The front-end also collects data via Google Analytics on how many users use the app over a certain period of time. 
> - Login with Google is used for the authentication of the users along with the basic login form.

### Back-end 

#### *In a nutshell, the back-end is divided into 3 modules:*
> ##### Web
> - The web module contains the controllers which handle the requests from the front-end.
> - It also contains the configuration for the application.
> ##### Service
> - The service module contains the business logic of the application.
> ##### Repository
> - The repository module contains the data access layer of the applicatio

#### *Detailed description*
> - To implement the back-end, I have used Java 15 along with the Spring framework with the following modules: 
>> - Spring Boot
>> - Spring Data
>> - Spring Security
>> - Spring MVC. 
> - I have used Gradle for the build automation tool. 
> - For the template engine, I have used FreeMarker. 
> - Other libraries in the project:
>> - Mapstruct
>> - Lombok
>> - Hibernate
>> - Hibernate Validator
>> - 
>> - H2 Database
>> - Google OAUTH2
> - Patterns such as MVC, Builder, DTO, Dependency Injection, and Proxy were used in the design all across of the application.
> - All connections are authorized, and the user should not be able to find vulnerable places in the application. 
> - The DTO helps to separate the structures of the individual entities in the database from the logic. 
> - An image compression algorithm was used to optimise the speed of the front-end.
> - I have also implemented exception controllers for unexpected actions by the user. 
> - As the project is open-source, I have also added space for environment variables so that private keys and API secrets can be held securely.


### Database
> - The database used in this application is H2 Database. Since it is an in-memory database, it does not need any configuration. 
> - The schema and the data are automatically generated when the application starts.

### Deployment
> - The application has been deployed on Heroku platform. 
> - Beyond that, I have used add-ons such as Librato and Raygun.

# Screenshots

<p align="center">
  <a href="https://github.com/Franek-Antoniak/picscore">
  <img src="https://i.imgur.com/dLvnzap.gif" alt="Gallery" width="1060"/></a>
</p>

# 

<p align="center">
<i>The project was originally commissioned by my high school computer science teacher. He needed a platform where his students could select the best photo in the class.</i>
</p>

#
<p align="center">
  <a href="https://github.com/Franek-Antoniak/picscore">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" alt="Java"/>
  </a>
  <a href="https://github.com/Franek-Antoniak/picscore">
  <img src="https://img.shields.io/badge/heroku-%23430098.svg?style=for-the-badge&logo=heroku&logoColor=white" alt="Heroku"/>
  </a>
  <a href="https://github.com/Franek-Antoniak/picscore">
  <img src="https://img.shields.io/badge/Gradle-%2302303A.svg?style=for-the-badge&logo=hibernate&logoColor=white" alt="Gradle"/>
  </a>
  <a href="https://github.com/Franek-Antoniak/picscore">
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Spring"/>
  </a>
  <a href="https://github.com/Franek-Antoniak/picscore">
  <img src="https://img.shields.io/badge/google-4285F4?style=for-the-badge&logo=google&logoColor=white" alt="Google"/>
  </a>
  <a href="https://github.com/Franek-Antoniak/picscore">
  <img src="https://img.shields.io/badge/bootstrap-%23563D7C.svg?style=for-the-badge&logo=bootstrap&logoColor=white" alt="Bootstrap"/>
  </a>
  <a href="https://github.com/Franek-Antoniak/picscore">
  <img src="https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E" alt="JavaScript"/>
  </a>
  <a href="https://github.com/Franek-Antoniak/picscore">
  <img src="https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white" alt="HTML5"/>
  </a>
  <a href="https://github.com/Franek-Antoniak/picscore">
  <img src="https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white" alt="CSS3"/>
  </a>
</p>
