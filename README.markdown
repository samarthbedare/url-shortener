# 🌐 URL Shortener

A full-stack URL Shortener built with **Java 21**, **Spring Boot 3.4.5**, **MongoDB**, and **React**. Easily shorten and redirect URLs with unique short codes.

## 📦 Tech Stack

- ⚙️ **Backend**: Spring Boot (Java 21, Maven)
- ☁️ **Database**: MongoDB Atlas (cloud-hosted)
- 💻 **Frontend**: React (Vite or Create React App)

## 🚀 Getting Started

### ✅ Prerequisites

- Java 21
- Maven
- MongoDB Atlas account
- Node.js & npm

## ⚙️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/url-shortener.git
cd url-shortener
```

### 2. Backend Setup

#### Configure MongoDB
Edit `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster0.mongodb.net/urlshortener?retryWrites=true&w=majority
app.base-url=http://localhost:8080
```

⚠️ **Note**: If your MongoDB password includes special characters (e.g., `@`, `#`, `%`), URL encode them (e.g., `@` becomes `%40`).

#### Run the Backend
```bash
./mvnw spring-boot:run
# or
mvn spring-boot:run
```

The backend server will be available at:  
🔗 [http://localhost:8080](http://localhost:8080)

#### CORS Configuration
CORS is pre-configured for frontend requests. For individual controllers, use:

```java
@CrossOrigin(origins = "*")
```

For global CORS, the following is included:

```java
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedOrigins("*");
        }
    };
}
```

### 3. Frontend Setup (React)

#### Navigate to the Frontend Folder
```bash
cd frontend
```

#### Install Dependencies
```bash
npm install
```

#### Configure API Endpoint
Create a `.env` file in the `frontend/` directory:

```env
VITE_API_BASE_URL=http://localhost:8080
```

#### Run the Frontend
```bash
npm run dev   # For Vite
# or
npm start     # For Create React App
```

The frontend will run at:  
🔗 [http://localhost:3000](http://localhost:3000)

## 📬 API Endpoints

### 🔹 POST `/shorten`
**Request**:
```json
{
  "url": "https://example.com"
}
```

**Response**:
```json
{
  "shortUrl": "http://localhost:8080/abc123"
}
```

### 🔹 GET `/{shortCode}`
Redirects to the original URL mapped to the given short code.

## 🤝 Contributing

Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -m 'Add YourFeature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Open a pull request.

## 📄 License

This project is licensed under the MIT License.

## 👨‍💻 Authors

**Samarth Narendra Bedare**  
🔗 [LinkedIn](https://linkedin.com/in/yourusername)  
💻 [GitHub](https://github.com/yourusername)

**Aditi Vaibhav Apte**  
🔗 [LinkedIn](https://linkedin.com/in/aditiapte15)  
💻 [GitHub](https://github.com/aditiapte15)
🔗 [LinkedIn](https://linkedin.com/in/samarthbedare)  
💻 [GitHub](https://github.com/samarthbedare)

**Aditi Vaibhav Apte**  
🔗 [LinkedIn](https://linkedin.com/in/aditiapte15)  
💻 [GitHub](https://github.com/aditiapte15)
