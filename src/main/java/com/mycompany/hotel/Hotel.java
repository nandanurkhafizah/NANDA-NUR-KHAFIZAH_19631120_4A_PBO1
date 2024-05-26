package com.mycompany.hotel;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Kelas Hotel merupakan representasi dari manajemen kamar hotel.

public class Hotel {
    // Atribut-atribut yang dimiliki oleh kelas Hotel.
    private ArrayList<Room> rooms;// Digunakan untuk menyimpan daftar kamar yang tersedia di hotel. Ini melengkapi point array.
    private ArrayList<User> users;    // Digunakan untuk menyimpan daftar pengguna yang terdaftar di sistem hotel. Ini melengkapi point array.
    private ArrayList<CheckoutReport> checkoutReports;    // Digunakan untuk menyimpan laporan checkout untuk setiap transaksi. Ini melengkapi point array.
    private Admin admin;
    private User currentUser;

    // Constructor untuk membuat objek Hotel baru.
    // Melengkapi poin Constructor.
    public Hotel() {
        rooms = new ArrayList<>();
        users = new ArrayList<>();
        checkoutReports = new ArrayList<>();
        admin = new Admin("admin", "admin");
        users.add(admin);
    }

    // Method untuk mendaftarkan pengguna baru.
    // Melengkapi poin Mutator.
    public void registerUser(String username, String password) {
        users.add(new User(username, password));
    }

    // Method untuk login pengguna.
    // Melengkapi poin Accessor.
    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.validateLogin(username, password)) {
                return user;
            }
        }
        return null;
    }

    // Method untuk menambahkan kamar baru.
    // Melengkapi poin Mutator.
    public void addRoom(String location, String roomName, double pricePerNight, String roomType) {
        rooms.add(new Room(location, roomName, pricePerNight, roomType));
    }

    // Method untuk menampilkan daftar kamar.
    // Melengkapi poin Accessor.
    public void displayRooms() {
        System.out.println("Lokasi | Nama Kamar | Harga | Tipe Kamar | Status Kamar | Pemesan");
        System.out.println("---------------------------------------------------------------");
        for (Room room : rooms) {
            String bookedBy = room.getBookedBy() != null ? room.getBookedBy().getName() : "None";
            System.out.printf("%-7s| %-11s| %-6.2f| %-11s| %-12s| %s%n", room.getLocation(), room.getRoomName(), room.getPricePerNight(), room.getRoomType(), room.isReady() ? "Ready" : "Not Ready", bookedBy);
        }
    }

    // Method untuk mengubah status kamar (check-in atau check-out).
    // Melengkapi poin Seleksi, Error Handling, Encapsulation.
    public void changeRoomStatus() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Pilih operasi:");
            System.out.println("1. Check-in\n2. Check-out");
            System.out.print("Pilih nomor: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Kamar yang siap:");
                    displayReadyRooms();
                    System.out.print("Masukkan nama kamar untuk check-in: ");
                    String roomName = scanner.nextLine();
                    Room selectedRoom = null;
                    for (Room room : rooms) {
                        if (room.getRoomName().equalsIgnoreCase(roomName) && room.isReady()) {
                            selectedRoom = room;
                            break;
                        }
                    }
                    if (selectedRoom != null) {
                        System.out.print("Masukkan nama penyewa: ");
                        String customerName = scanner.nextLine();
                        System.out.print("Masukkan nomor telepon: ");
                        String phoneNumber = scanner.nextLine();
                        System.out.print("Masukkan tanggal check-in (yyyy-mm-dd): ");
                        String checkInDate = scanner.nextLine();
                        Customer customer = new Customer(customerName, phoneNumber, checkInDate);
                        selectedRoom.bookRoom(customer);
                        System.out.println(customerName + " sudah check-in di kamar " + roomName);
                    } else {
                        System.out.println("Kamar tidak tersedia atau tidak siap.");
                    }
                    break;
                case 2:
                    System.out.println("Kamar yang tidak ready:");
                    displayNotReadyRooms();
                    System.out.print("Masukkan nama kamar untuk check-out: ");
                    roomName = scanner.nextLine();
                    for (Room room : rooms) {
                        if (!room.isReady() && room.getRoomName().equalsIgnoreCase(roomName)) {
                            Customer customer = room.getBookedBy();
                            System.out.print("Masukkan tanggal check-out (yyyy-mm-dd): ");
                            String checkOutDate = scanner.nextLine();
                            customer.setCheckOutDate(checkOutDate);
                            room.releaseRoom();
                            System.out.println("Check-out dari kamar " + roomName + " berhasil.");
                            addToReport(customer, room, checkOutDate);
                            return;
                        }
                    }
                    System.out.println("Kamar tidak ditemukan atau belum dipesan.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } catch (InputMismatchException e) {
            // Melengkapi poin Error Handling.
            System.out.println("Input tidak valid. Harap masukkan nomor yang benar.");
            scanner.nextLine(); // membersihkan input yang tidak valid
        }
    }

    // Method untuk menampilkan daftar kamar yang siap.
    // Melengkapi poin Perulangan.
    public void displayReadyRooms() {
        System.out.println("Lokasi | Nama Kamar | Harga | Tipe Kamar | Status Kamar | Pemesan");
        System.out.println("---------------------------------------------------------------");
        for (Room room : rooms) {
            if (room.isReady()) {
                String bookedBy = room.getBookedBy() != null ? room.getBookedBy().getName() : "None";
                System.out.printf("%-7s| %-11s| %-6.2f| %-11s| %-12s| %s%n", room.getLocation(), room.getRoomName(), room.getPricePerNight(), room.getRoomType(), room.isReady() ? "Ready" : "Not Ready", bookedBy);
            }
        }
    }

    // Method untuk menampilkan daftar kamar yang tidak siap.
    // Melengkapi poin Perulangan, Encapsulation.
    public void displayNotReadyRooms() {
        System.out.println("Nama Penyewa | Lokasi | Nama Kamar | Harga | Tipe Kamar");
        System.out.println("---------------------------------------------------------");
        for (Room room : rooms) {
            if (!room.isReady() && room.getBookedBy() != null) {
                System.out.printf("%-13s| %-7s| %-11s| %-6.2f| %-11s%n", room.getBookedBy().getName(), room.getLocation(), room.getRoomName(), room.getPricePerNight(), room.getRoomType());
            }
        }
    }

    // Method untuk menambahkan laporan check-out.
    // Melengkapi poin Mutator, Array.
    public void addToReport(Customer customer, Room room, String checkOutDate) {
        checkoutReports.add(new CheckoutReport(customer, room, checkOutDate));
    }

    // Method untuk menampilkan laporan check-out.
    // Melengkapi poin IO Sederhana.
    public void displayCheckoutReports() {
        System.out.println("Laporan Kamar yang Sudah Checkout:");
               System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-15s | %-10s | %-10s | %-10s | %-15s | %-15s%n", "Nama Penyewa", "Nomor Kamar", "Tipe Kamar", "Harga", "Tanggal Masuk", "Tanggal Keluar");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (CheckoutReport report : checkoutReports) {
            Customer customer = report.getCustomer();
            Room room = report.getRoom();
            String checkInDate = customer.getCheckInDate();
            String checkOutDate = customer.getCheckOutDate();
            System.out.printf("%-15s | %-10s | %-10s | %-10.2f | %-15s | %-15s%n",
                    customer.getName(),
                    room.getRoomName(),
                    room.getRoomType(),
                    room.getPricePerNight(),
                    checkInDate,
                    checkOutDate);
        }
    }

    // Method main sebagai entry point aplikasi.
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Pilih nomor:");
                System.out.println("1. Register\n2. Login\n3. Keluar");
                System.out.print("Pilih nomor: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Masukkan nama pengguna: ");
                        String username = scanner.nextLine();
                        System.out.print("Masukkan kata sandi: ");
                        String password = scanner.nextLine();
                        hotel.registerUser(username, password);
                        System.out.println("Pengguna berhasil didaftarkan.");
                        break;
                    case 2:
                        System.out.print("Masukkan nama pengguna: ");
                        username = scanner.nextLine();
                        System.out.print("Masukkan kata sandi: ");
                        password = scanner.nextLine();
                        hotel.currentUser = hotel.loginUser(username, password);
                        if (hotel.currentUser != null) {
                            System.out.println("Login berhasil.");
                            boolean loggedIn = true;
                            while (loggedIn) {
                                try {
                                    System.out.println("Pilih nomor:");
                                    System.out.println("1. Tambah Kamar\n2. Tampilkan Kamar\n3. Ubah Status Kamar\n4. Tampilkan Laporan Checkout\n5. Logout");
                                    System.out.print("Pilih nomor: ");
                                    int adminChoice = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (adminChoice) {
                                        case 1:
                                            System.out.print("Masukkan lokasi: ");
                                            String location = scanner.nextLine();
                                            System.out.print("Masukkan nama kamar: ");
                                            String roomName = scanner.nextLine();
                                            System.out.print("Masukkan harga per malam: ");
                                            double pricePerNight = scanner.nextDouble();
                                            scanner.nextLine();
                                            System.out.print("Masukkan tipe kamar: ");
                                            String roomType = scanner.nextLine();
                                            hotel.addRoom(location, roomName, pricePerNight, roomType);
                                            System.out.println("Kamar berhasil ditambahkan.");
                                            break;
                                        case 2:
                                            hotel.displayRooms();
                                            break;
                                        case 3:
                                            hotel.changeRoomStatus();
                                            break;
                                        case 4:
                                            hotel.displayCheckoutReports();
                                            break;
                                        case 5:
                                            loggedIn = false;
                                            break;
                                        default:
                                            System.out.println("Pilihan tidak valid.");
                                    }
                                } catch (InputMismatchException e) {
                                    // Melengkapi poin Error Handling.
                                    System.out.println("Input tidak valid. Harap masukkan nomor yang benar.");
                                    scanner.nextLine(); // membersihkan input yang tidak valid
                                }
                            }
                        } else {
                            System.out.println("Kredensial tidak valid.");
                        }
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                // Melengkapi poin Error Handling.
                System.out.println("Input tidak valid. Harap masukkan nomor yang benar.");
                scanner.nextLine(); // membersihkan input yang tidak valid
            }
        }
    }
}

