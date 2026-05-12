# TrainingPage statistic-service

## Description 

A fitness application built with multiple microservices to help you with planning, logging and improving your home training.
The system is a microservice application built with Spring Boot, React, and PostgreSQL and containerized using Docker for easy setup and deployment.


Statistics-service retrieves data from training-service and user-service through OpenFeign HTTP API calls to calculate statistics. 


## Technology stack

* Java Spring Boot
* OpenFeign
* Eureka Client


## Endpoints
| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| GET | /statistic/stats | Fetch user's statistics | Yes |
| GET | /statistic/session | Fetch the user's sessions | Yes |
| GET | /statistic/period/stats | Fetch statistics for a specific period | Yes |
| GET | /statistic/period/session | Fetch sessions for a specific period  | Yes |
| GET | /statistic/chart | Fetch exercise distribution chart for a user | Yes |
| GET | /statistic/users | Fetch the global leaderboard | Yes |

### Query Parameters
Most endpoints require an id (User ID) as a query parameter. Period-specific endpoints also require startDate and endDate.

Example: Period Statistics
GET /statistic/period/stats?id=1&startDate=2026-05-01&endDate=2026-05-20

Example: User Chart
GET /statistic/chart?id=1

## Setup

This service is designed to be run as part of the Docker Compose cluster.
For instructions on how to start the project go to the readme file at [trainingpage](https://github.com/TheSteinkamp/trainingpage.git)
