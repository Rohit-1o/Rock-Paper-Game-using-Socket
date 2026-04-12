# 🎮 Rock Paper Scissors - Multiplayer Game

## 📌 Project Overview

This is a **real-time multiplayer Rock Paper Scissors game** built using **Java WebSocket and HTML/CSS/JavaScript**.
Two players can connect to the server and play the game live, with instant result updates.

---

## 🚀 Features

* 👥 Multiplayer support (2 players)
* ⚡ Real-time communication using WebSockets
* 🎯 Instant result calculation
* 🔄 Continuous gameplay (multiple rounds)
* 📡 JSON-based client-server communication

---

## 🛠️ Tech Stack

* **Frontend:** HTML, CSS, JavaScript
* **Backend:** Java (WebSocket API)
* **Protocol:** WebSocket (TCP-based communication)

---

## 📂 Project Structure

```
RPS-Game/
│
├── server/
│   └── RPSServer.java
│
├── client/
│   ├── index.html
│   ├── style.css
│   └── script.js
```

---

## ⚙️ How to Run the Project

### 1️⃣ Start the Server

* Run the Java WebSocket server (deploy using Tomcat or any WebSocket-supported server)
* Endpoint: `ws://localhost:8080/game`

---

### 2️⃣ Run the Client

* Open `index.html` in browser
* Open **2 tabs** to simulate 2 players

---

### 3️⃣ Play the Game

* Choose:

  * 🪨 Rock
  * 📄 Paper
  * ✂️ Scissors
* Result will be displayed instantly

---

## 🧠 Game Logic

* Rock beats Scissors
* Scissors beats Paper
* Paper beats Rock
* Same move → Draw

---

## 📸 Future Enhancements

* 🏆 Scoreboard system
* 💬 Chat feature
* 🎨 Improved UI/UX
* ⏱️ Timer-based moves
* 🌐 Multiple game rooms

---

## 👨‍💻 Author

**Rohit Goud**
Computer Engineering Student

---

## ⭐ Acknowledgment

This project was built as part of learning **Socket Programming and Real-Time Applications**.
