package com.mycompany.hotel;

// Kelas Customer merepresentasikan pelanggan yang melakukan check-in di hotel.

public class Customer {
    // Atribut-atribut yang dimiliki oleh kelas Customer.
    private String name;
    private String phoneNumber;
    private String checkInDate;
    private String checkOutDate;

    // Constructor untuk membuat objek Customer baru.
    // Melengkapi poin Constructor.
    public Customer(String name, String phoneNumber, String checkInDate) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.checkInDate = checkInDate;
    }

    // Method untuk mengambil nama pelanggan.
    // Melengkapi poin Accessor.
    public String getName() {
        return name;
    }

    // Method untuk mengambil nomor telepon pelanggan.
    // Melengkapi poin Accessor.
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Method untuk mengambil tanggal check-in pelanggan.
    // Melengkapi poin Accessor.
    public String getCheckInDate() {
        return checkInDate;
    }

    // Method untuk mengambil tanggal check-out pelanggan.
    // Melengkapi poin Accessor.
    public String getCheckOutDate() {
        return checkOutDate;
    }

    // Method untuk mengatur tanggal check-out pelanggan.
    // Melengkapi poin Mutator.
    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
