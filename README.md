# Real-time Chat Application

A full-stack real-time chat application built with Spring Boot and Angular, featuring WebSocket integration for instant messaging and comprehensive message management.

## 🚀 Features

- **Real-time Messaging**: Instant message delivery using WebSocket technology
- **Chat Rooms**: Create and join multiple chat rooms
- **Message History**: Persistent message storage with retrieval functionality


## 🛠️ Tech Stack

### Backend
- **Spring Boot** - Main framework
- **Spring Data JPA** - Database operations
- **WebSocket** - Real-time communication
- **MySQL** - Database
- **Maven** - Dependency management

### Frontend
- **Angular** - Frontend framework
- **TypeScript/JavaScript** - Programming language
- **HTML5/CSS3** - Markup and styling

<!-- ## 📁 Project Structure

```
real-time-chatapp/
├── backend/
│   ├── src/main/java/org/chatapp/chatonline/
│   │   ├── messagecontent/
│   │   │   ├── MessageContent.java
│   │   │   ├── MessageContentRepository.java
│   │   │   └── MessageContentService.java
│   │   ├── messageroom/
│   │   └── user/
│   └── pom.xml
└── frontend/
    ├── src/
    └── package.json
``` -->

## 🏗️ Architecture

### Entity Relationships
- **MessageContent**: Stores individual messages with UUID primary keys
- **MessageRoom**: Manages chat rooms and participants  
- **User**: Handles user information and authentication

### Key Repository Methods
- `findTopByMessageRoomIdOrderByDateSentDesc()` - Retrieves latest message in a room
- Custom JPA queries for message history and room management

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Node.js 16+
- MySQL 8.0+
- Maven 3.6+

### Backend Setup
1. Clone the repository
```bash
git clone https://github.com/GiaLongDangPham/real-time-chatapp.git
cd real-time-chatapp/backend
```

2. Configure database in `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/chatapp_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Run the Spring Boot application
```bash
mvn spring-boot:run
```

### Frontend Setup
1. Navigate to frontend directory
```bash
cd ../frontend
```

2. Install dependencies
```bash
npm install
```

3. Start the development server
```bash
npm start
```

## 🔌 API Endpoints

### Messages
- `GET /api/messages/{roomId}` - Get message history
- `POST /api/messages` - Send new message
- `GET /api/messages/{roomId}/latest` - Get latest message

### Rooms
- `GET /api/rooms` - List all rooms
- `POST /api/rooms` - Create new room
- `GET /api/rooms/{id}` - Get room details

### WebSocket
- `/ws/chat` - WebSocket endpoint for real-time messaging

## 🎯 Key Features in Detail

### Message Management
- UUID-based entity identification for scalability
- Efficient message retrieval with custom JPA queries
- Automatic timestamp tracking for message ordering

### Real-time Communication
- WebSocket integration for instant message delivery
- Room-based message broadcasting
- Connection state management

### Database Design
- Optimized entity relationships
- Proper indexing for message queries
- Foreign key constraints for data integrity

## 🤝 Contributing

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

