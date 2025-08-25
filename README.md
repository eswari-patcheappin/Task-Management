# Task Management System

A comprehensive task management application built with Spring Boot backend, MySQL database, and a responsive HTML/CSS/JavaScript frontend.

## Features

### Backend (Spring Boot + Hibernate)
- RESTful API endpoints for complete task management
- MySQL database integration with Hibernate ORM
- Task creation, assignment, deadlines, and status updates
- Advanced filtering and search capabilities
- Automatic timestamp tracking

### Frontend (HTML/CSS/JavaScript)
- Modern, responsive user interface
- Real-time task statistics dashboard
- Advanced filtering and search functionality
- Modal-based task creation and editing
- Priority and status management
- Deadline tracking with overdue notifications

### API Endpoints

#### Task Management
- `GET /api/tasks` - Get all tasks
- `POST /api/tasks` - Create a new task
- `GET /api/tasks/{id}` - Get task by ID
- `PUT /api/tasks/{id}` - Update a task
- `DELETE /api/tasks/{id}` - Delete a task

#### Task Operations
- `PUT /api/tasks/{id}/status` - Update task status
- `PUT /api/tasks/{id}/assign` - Assign task to someone
- `PUT /api/tasks/{id}/deadline` - Set task deadline

#### Filtering & Search
- `GET /api/tasks/status/{status}` - Get tasks by status
- `GET /api/tasks/priority/{priority}` - Get tasks by priority
- `GET /api/tasks/assigned/{assignedTo}` - Get tasks by assignee
- `GET /api/tasks/overdue` - Get overdue tasks
- `GET /api/tasks/search?title={title}` - Search tasks by title
- `GET /api/tasks/upcoming` - Get tasks with upcoming deadlines

## Technology Stack

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Hibernate ORM**
- **MySQL 8.0**
- **Maven** (Build tool)

### Frontend
- **HTML5**
- **CSS3** (Responsive design)
- **Vanilla JavaScript** (ES6+)
- **Fetch API** for HTTP requests

## Prerequisites

Before running this application, make sure you have:

1. **Java 17** or higher installed
2. **Maven 3.6** or higher installed
3. **MySQL 8.0** or higher installed and running
4. **Git** (optional, for version control)

## Database Setup

1. **Install MySQL** (if not already installed)
2. **Create a database:**
   ```sql
   CREATE DATABASE task_management_db;
   ```
3. **Create a MySQL user** (optional but recommended):
   ```sql
   CREATE USER 'taskuser'@'localhost' IDENTIFIED BY 'taskpass';
   GRANT ALL PRIVILEGES ON task_management_db.* TO 'taskuser'@'localhost';
   FLUSH PRIVILEGES;
   ```

4. **Update database configuration** in `backend/src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/task_management_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd "Task Management"
```

### 2. Backend Setup
```bash
# Navigate to backend directory
cd backend

# Install dependencies and build
mvn clean install

# Run the application
mvn spring-boot:run
```

The backend server will start on `http://localhost:8080`

### 3. Frontend Setup
```bash
# Navigate to frontend directory
cd ../frontend

# Open index.html in a web browser
# Or serve using a simple HTTP server (optional)
# For Python 3:
python -m http.server 3000

# For Node.js (if you have http-server installed):
npx http-server -p 3000
```

The frontend will be available at `http://localhost:3000` (if using a server) or directly by opening `index.html`

## Usage

### Creating Tasks
1. Click **"Add New Task"** button
2. Fill in the task details:
   - Title (required)
   - Description (optional)
   - Status (Pending, In Progress, Completed, Cancelled)
   - Priority (Low, Medium, High, Urgent)
   - Assigned To (optional)
   - Deadline (optional)
3. Click **"Save Task"**

### Managing Tasks
- **Edit**: Click the "Edit" button on any task card
- **Complete**: Click the "Complete" button to mark as completed
- **Start**: Click the "Start" button to mark as in progress
- **Delete**: Click the "Delete" button (with confirmation)

### Filtering & Search
- **Status Filter**: Filter tasks by status using the dropdown
- **Priority Filter**: Filter tasks by priority using the dropdown
- **Search**: Type in the search box to find tasks by title, description, or assignee

### Dashboard Statistics
The dashboard shows real-time statistics:
- Total Tasks
- Pending Tasks
- In Progress Tasks
- Completed Tasks
- Overdue Tasks

## Database Schema

### Tasks Table
```sql
CREATE TABLE tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED') NOT NULL DEFAULT 'PENDING',
    priority ENUM('LOW', 'MEDIUM', 'HIGH', 'URGENT') NOT NULL DEFAULT 'MEDIUM',
    assigned_to VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deadline TIMESTAMP NULL
);
```

## API Documentation

### Task Object Structure
```json
{
  "id": 1,
  "title": "Sample Task",
  "description": "Task description",
  "status": "PENDING",
  "priority": "MEDIUM",
  "assignedTo": "John Doe",
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00",
  "deadline": "2024-01-15T17:00:00"
}
```

### Sample API Calls

#### Create a Task
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Complete project documentation",
    "description": "Write comprehensive documentation for the task management system",
    "priority": "HIGH",
    "assignedTo": "John Doe",
    "deadline": "2024-12-31T17:00:00"
  }'
```

#### Update Task Status
```bash
curl -X PUT http://localhost:8080/api/tasks/1/status \
  -H "Content-Type: application/json" \
  -d '{"status": "IN_PROGRESS"}'
```

## Development

### Project Structure
```
Task Management/
├── backend/
│   ├── src/main/java/com/taskmanagement/
│   │   ├── controller/     # REST controllers
│   │   ├── model/         # Entity classes
│   │   ├── repository/    # Data repositories
│   │   ├── service/       # Business logic
│   │   └── TaskManagementApplication.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
├── frontend/
│   ├── index.html        # Main HTML file
│   ├── styles.css        # Stylesheet
│   └── script.js         # JavaScript functionality
└── README.md
```

### Adding New Features

1. **Backend**: Add new endpoints in controllers, implement business logic in services
2. **Frontend**: Update the JavaScript API calls and UI components
3. **Database**: Modify the entity classes and let Hibernate handle schema updates

## Troubleshooting

### Common Issues

1. **Connection refused error**
   - Ensure MySQL is running
   - Check database credentials in `application.properties`
   - Verify the database exists

2. **Port already in use**
   - Change the server port in `application.properties`:
     ```properties
     server.port=8081
     ```

3. **CORS issues**
   - The backend is configured to allow all origins
   - For production, restrict CORS origins in the controller

4. **Build fails**
   - Ensure Java 17 is installed and set as JAVA_HOME
   - Run `mvn clean install` to refresh dependencies

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is open source and available under the [MIT License](LICENSE).

## Future Enhancements

- User authentication and authorization
- Real-time notifications
- Task comments and attachments
- Advanced reporting and analytics
- Mobile application
- Email notifications for deadlines
- Team management features
- Task templates
- Bulk operations
- Export functionality
