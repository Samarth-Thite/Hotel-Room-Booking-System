# 🏨 Hotel Room Booking System (Console-Based)

A simple **console-based Java application** that simulates a basic hotel room booking system using:

✅ Core Java (OOP, JDBC, Exception Handling)  
✅ PostgreSQL Database  
✅ Simple Project Structure (no Maven)

---

## 📂 Project Structure

Hotel-Room-Booking-System/
├── src/
│ └── Hotel_Booking/
│ └── Hotel_Booking/
│ └── HotelRoomBookingSystem.java
├── README.md
└── .gitignore

sql
Copy
Edit

---

## 🗃️ Database Setup (PostgreSQL)

Run these SQL commands in your PostgreSQL database to set up required tables:

```sql
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    phone VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS rooms (
    id SERIAL PRIMARY KEY,
    room_number VARCHAR(10),
    type VARCHAR(50),
    price DECIMAL(10,2),
    available BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS bookings (
    booking_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    room_id INT REFERENCES rooms(id) ON DELETE SET NULL,
    check_in DATE,
    check_out DATE,
    status VARCHAR(20)
);
⚙️ Configuration
Edit the PostgreSQL credentials in your HotelRoomBookingSystem.java file:

java
Copy
Edit
String url = "jdbc:postgresql://localhost:5432/hotel_booking_db";
String user = "postgres";
String password = "root123";
🚀 How to Run
Clone this repo or copy the project folder.

Set up PostgreSQL and create the required tables.

Open the project in Eclipse or any Java IDE.

Compile and run HotelRoomBookingSystem.java.

Use the console menu to:

Register users

View available rooms

Book a room

🧠 Features
✔️ Register new users
✔️ View all available rooms
✔️ Book rooms with check-in and check-out dates
✔️ Updates room availability after booking

💡 Future Improvements
GUI integration (Swing/JavaFX)

Online payment gateway integration

Email or SMS confirmations for bookings

User authentication and admin panel

🧑‍💻 Author
Made with ☕ and 💡 by Samarth Thite.

