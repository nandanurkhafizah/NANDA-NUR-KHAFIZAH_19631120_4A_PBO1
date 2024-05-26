package com.mycompany.hotel;

// Class Admin merupakan turunan dari kelas User.
// Ini adalah contoh dari inheritance.

public class Admin extends User {
    // Constructor untuk membuat objek Admin baru.
    // Melengkapi point Constructor.
    public Admin(String username, String password) {
        super(username, password); // Memanggil constructor dari kelas induk (User).
    }
}
