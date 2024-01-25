# Foro-Alura Backend

Welcome to our Foro-Alura!

The Alura Forum serves as a space where all Alura platform students can post their questions about specific courses. This magical place is filled with abundant learning opportunities and collaboration among students, instructors, and moderators.

I use the DTO and MVC pattern to create the API, and I use the Spring Data JPA to persist the data in a MySQL database.
The records set the types of elements that receive the data, DTOs are the objects that will be sent to the API.
The Repository is the interface that will be responsible for the persistence of the data.
Besides that, the model is the entity that will be persisted in the database.
## Project Overview

Our API will specifically focus on topics and must allow users to:

- Create a new topic
- Display all created topics
- Display a specific topic
- Update a topic
- Delete a topic
- Add users
- Create courses
  

This corresponds to the common CRUD operations (CREATE, READ, UPDATE, DELETE) and is quite similar to what we developed in the Alura Hotel, but now using a framework that will significantly facilitate our work: the REST architectural style.

By the end of this sprint, our REST API will have the following features:

- API with implemented routes following the best practices of the REST model
- Validations performed according to business rules
- Implementation of a database for data persistence

## SpringDocs

All the documentation of the API will be in the next path:
http://localhost:8180/swagger-ui/index.html

## API Routes

The API follows RESTful conventions. Here are some of the key routes:

<hr>
# POST /api/newtopics: Create a new topic
<hr>
# GET /api/topics: Display all topics
<hr>
# GET /api/newtopics/all/{id} Display a specific topic by ID
<hr>
# PUT /api/newtopics/all/update: Update a topic by ID, add in the payload the information about the user to update
<hr>
# DELETE /api/topics/{id}: Delete a topic by ID
<hr>
# POST /api/newuser: Add a new user
<hr>
# POST /api/newcourse: Add a new course

## Getting Started

Let's get to work! We have a three-week time frame to develop the project. In this opportunity, we're providing the entity model part of the project, allowing you to focus on developing the other functionalities and make the API fully functional. Feel free to use your creativity and make modifications as you see fit.

We will follow the agile development approach, using Trello as follows:

- **Ready to Start:** Cards with elements that have not been developed yet.
- **In Progress:** Elements currently being developed. When starting a task, move the corresponding card to this column.
- **Paused:** Elements that you started to develop but had to pause for some reason.
- **Completed:** Elements that are already completed.

Trello is an individual use tool to help you track the progress of your activities but will not be evaluated.

Good luck with your project!
