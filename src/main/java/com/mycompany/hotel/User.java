package com.mycompany.hotel;

// Kelas User merupakan kelas yang merepresentasikan pengguna hotel.

public class User {
    // Atribut-atribut yang dimiliki oleh kelas User.
    private String username;
    private String password;

    // Constructor untuk membuat objek User baru.
    // Melengkapi poin Constructor.
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Method untuk memvalidasi login pengguna.
    // Melengkapi poin Seleksi.
    public boolean validateLogin(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Method untuk mengambil username pengguna.
    // Melengkapi poin Accessor.
    public String getUsername() {
        return username;
    }

    // Method untuk mengambil password pengguna.
    // Melengkapi poin Accessor.
    public String getPassword() {
        return password;
    }
}
