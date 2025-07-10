package Hotel_Booking.Hotel_Booking;
import java.sql.*;
import java.util.Scanner;

public class HotelRoomBookingSystem {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/hotel_booking_db";
        String user = "postgres";
        String password = "104508";

        try (Connection conn = DriverManager.getConnection(url,user,password);
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n🏨 Hotel Room Booking System Menu:");
                System.out.println("1. Register User");
                System.out.println("2. View Available Rooms");
                System.out.println("3. Book Room");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = sc.nextLine();

                    String insertUser = "INSERT INTO users (name, phone) VALUES (?, ?)";
                    try (PreparedStatement stmt = conn.prepareStatement(insertUser)) {
                        stmt.setString(1, name);
                        stmt.setString(2, phone);
                        stmt.executeUpdate();
                        System.out.println("✅ User registered successfully.");
                    }

                } else if (choice == 2) {
                    String selectRooms = "SELECT * FROM rooms WHERE available = true";
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery(selectRooms)) {
                        System.out.println("Available Rooms:");
                        while (rs.next()) {
                            System.out.println(rs.getInt("id") + " " +
                                               rs.getString("room_number") + " " +
                                               rs.getString("type") + " ₹" +
                                               rs.getDouble("price"));
                        }
                    }

                } else if (choice == 3) {
                    System.out.print("Enter user ID: ");
                    int userId = sc.nextInt();
                    System.out.print("Enter room ID: ");
                    int roomId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter check-in date (yyyy-mm-dd): ");
                    Date checkIn = Date.valueOf(sc.nextLine());
                    System.out.print("Enter check-out date (yyyy-mm-dd): ");
                    Date checkOut = Date.valueOf(sc.nextLine());

                    String insertBooking = "INSERT INTO bookings (user_id, room_id, check_in, check_out, status) VALUES (?, ?, ?, ?, 'booked')";
                    String updateRoom = "UPDATE rooms SET available = false WHERE id = ?";

                    try (PreparedStatement stmt = conn.prepareStatement(insertBooking);
                         PreparedStatement updStmt = conn.prepareStatement(updateRoom)) {
                        stmt.setInt(1, userId);
                        stmt.setInt(2, roomId);
                        stmt.setDate(3, checkIn);
                        stmt.setDate(4, checkOut);
                        stmt.executeUpdate();

                        updStmt.setInt(1, roomId);
                        updStmt.executeUpdate();

                        System.out.println("✅ Room booked successfully.");
                    }

                } else if (choice == 4) {
                    System.out.println("Exiting...");
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



    