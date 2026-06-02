# Airguitar

Airguitar is an Airbnb-style marketplace for renting musical instruments.  
This repository uses a modular monolith backend (Spring Boot) and a React + TypeScript frontend.

## Stack

- Backend: Java 17, Spring Boot, Spring Security (JWT), Spring Data JPA, MySQL, WebSocket/STOMP
- Frontend: React + TypeScript (Vite), React Router, MUI, Axios, STOMP client
- Infra: Docker Compose (MySQL)

## Features Implemented

- JWT auth (`/auth/register`, `/auth/login`) with BCrypt password hashing
- Instrument CRUD (`/instruments`)
- Booking creation + overlap prevention (`/bookings`, `/bookings/my`)
- 1:1 chat with persisted history (`GET /messages/{instrumentId}/{userId}`)
- WebSocket endpoint (`/ws/chat`) for real-time messages
- Event-driven notifications with Spring application events:
  - `BookingCreatedEvent`
  - `MessageSentEvent`
- Notification persistence + real-time push (`/notifications/my`, `/notifications/{id}/read`)

## Run

### 1) Start MySQL

```bash
docker compose up -d
```

### 2) Backend

```bash
cd backend
./mvnw spring-boot:run
```

Backend runs on `http://localhost:8080`.

### 3) Frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on `http://localhost:5173`.

## Notes

- JWT token is stored in `localStorage` by the frontend.
- Notification and chat updates are pushed via STOMP topics.
- JPA schema generation is enabled via `spring.jpa.hibernate.ddl-auto=update` for MVP speed.
