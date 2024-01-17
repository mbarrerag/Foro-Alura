# Foro-Alura Backend

Welcome to our Foro-Alura!

The Alura Forum serves as a space where all Alura platform students can post their questions about specific courses. This magical place is filled with abundant learning opportunities and collaboration among students, instructors, and moderators.

Now that we know what the forum is for and how it looks, let's dive into how it works behind the scenes. Where is the information stored? How are data processed to associate a topic with a response, or how are users related to the responses in a topic?

Our challenge is to replicate this process at the backend level, creating a REST API using Spring.

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

## API Routes

The API follows RESTful conventions. Here are some of the key routes:

POST /api/newtopics: Create a new topic
GET /api/topics: Display all topics
GET /api/newtopics/all/{id} Display a specific topic by ID
PUT /api/newtopics/all/update: Update a topic by ID, add in the payload the information about the user to update
DELETE /api/topics/{id}: Delete a topic by ID
POST /api/newuser: Add a new user
POST /api/newcourse: Add a new course

## Getting Started

Let's get to work! We have a three-week time frame to develop the project. In this opportunity, we're providing the entity model part of the project, allowing you to focus on developing the other functionalities and make the API fully functional. Feel free to use your creativity and make modifications as you see fit.

We will follow the agile development approach, using Trello as follows:

- **Ready to Start:** Cards with elements that have not been developed yet.
- **In Progress:** Elements currently being developed. When starting a task, move the corresponding card to this column.
- **Paused:** Elements that you started to develop but had to pause for some reason.
- **Completed:** Elements that are already completed.

Trello is an individual use tool to help you track the progress of your activities but will not be evaluated.

Good luck with your project!
