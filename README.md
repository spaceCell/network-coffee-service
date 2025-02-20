# **Technical description of the project**

📌 **Project:** Social application
📌 **Technologies:** Java 21, Spring Boot 3, PostgreSQL, WebFlux, JWT, Docker, Lombok, Prometheus, Grafana, Swagger, JavaScript

---

## **1. Project description**
The project is a **reactive web application** for working with user subscriptions.
Users can:
✅ **Register and log in** (JWT)
✅ **Subscribe to each other**
✅ **View your subscribers**
✅ **Delete subscribers**

The backend is implemented on **Spring Boot 3 (WebFlux)**, the UI is written in **pure JavaScript**.
The project is built and launched in **Docker** (the database is also raised in the container).

---

## **2. Main technologies**
- **Java 21**, **Spring Boot 3**, **Spring WebFlux** — reactive architecture
- **PostgreSQL** — database
- **JWT** — authentication
- **Swagger** — API and DTO autogeneration
- **Lombok** — boilerplate code reduction
- **JUnit** — testing
- **Prometheus, Grafana** — monitoring
- **Docker, Docker Compose** — deployment

---

## **3. Project launch**
### **➤ 3.1 Launch in Docker**
Make sure **Docker** and **Docker Compose** are installed.
1. Clone the repository:
```sh
git clone https://github.com/your-repo/social-app.git
cd social-app
```
2. Build and run containers:
```sh
docker-compose up --build
```
3. Open the API in Swagger:
- **http://localhost:8080/swagger-ui.html**

---

## **4. Configuration (Environment Variables)**
| Variable | Description | Default value |
|-------------------|--------------------------------|------------------------|
| `SPRING_DATASOURCE_URL` | DB connection | `jdbc:postgresql://db:5432/mydatabase` |
| `SPRING_DATASOURCE_USERNAME` | DB login | `admin` |
| `SPRING_DATASOURCE_PASSWORD` | Database password | `secret` |
| `JWT_SECRET` | Secret for signing JWT | `your_secret_key` |
| `SERVER_PORT` | Service startup port | `8080` |

Values ​​can be changed in the `.env` file or at startup:
```sh
export SERVER_PORT=9090
docker-compose up
```

---

## **5. API Endpoints**
### **➤ 5.1 Authentication**
| Method | URL | Description |
|-------|----------|
| `POST` | `/api/v1/auth/register` | Register a new user |
| `POST` | `/api/v1/auth/login` | Login (JWT) |

### **➤ 5.2 Subscriptions**
| Method | URL | Description |
|-------|-----|---------|
| `POST` | `/api/v1/subscriptions/{userId}` | Subscribe to a user |
| `DELETE` | `/api/v1/subscriptions/{userId}` | Unsubscribe |
| `GET` | `/api/v1/subscriptions/{userId}/followers` | Get a list of subscribers |

---

## **6. Running tests**
The project includes **JUnit** and **Spring Boot Test** tests.
You can run tests with the command:
```sh
mvn test
```

---

## **7. UI (Frontend)**
The project has a **simple UI in JavaScript** (`frontend/`):
- `index.html` — login form
- `profile.html` — view subscribers

You can run via any local server, for example:
```sh
cd frontend
python -m http.server 8081
```
Open in browser: **http://localhost:8081/index.html**

---

## **8. Monitoring (Prometheus, Grafana)**
The service sends **metrics** to **Prometheus**, which can be analyzed in **Grafana**.

1. **Start monitoring:**
```sh
docker-compose -f monitoring-compose.yml up -d
```
2. **Open Grafana:**
- **http://localhost:3000** (login: `admin`, password: `admin`)
3. **Configure data source:**
- Prometheus: **http://prometheus:9090**

---

## **9. Development**
**Build and run in Dev mode:**
```sh
mvn spring-boot:run
```

**Restart on code changes:**
```sh
mvn spring-boot:devtools
```

---

## **10. Deployment**
The finished **Dockerfile** allows you to build the image:
```sh
docker build -t social-app .
docker run -p 8080:8080 social-app
```

---

## **11. TODO / Improvements**
- ✅ **Database and API**
- ✅ **Authentication via JWT**

- ✅ **Reactive architecture (WebFlux)**

- 🔲 **Add WebSocket notifications**

- 🔲 **Create a full-fledged UI (React / Vue.js)**

- 🔲 **Deploy to server (Kubernetes / AWS)**

---

✉ **Contacts**
📌 Author: **https://github.com/spaceCell**
📌 Email: **kirill.space.cell@tuta.io**

📌 Telegram: **@roadthelife**

🚀 **Development is active, PRs are welcome!**