package com.mycompany.hotel;

// Kelas CheckoutReport digunakan untuk merepresentasikan laporan checkout kamar hotel.

public class CheckoutReport {
    // Atribut-atribut yang dimiliki oleh kelas CheckoutReport.
    private Customer customer;
    private Room room;
    private String checkOutDate;

    // Constructor untuk membuat objek CheckoutReport baru.
    // Melengkapi poin Constructor.
    public CheckoutReport(Customer customer, Room room, String checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkOutDate = checkOutDate;
    }

    // Method untuk mengambil informasi pelanggan.
    // Melengkapi poin Accessor.
    public Customer getCustomer() {
        return customer;
    }

    // Method untuk mengambil informasi kamar.
    // Melengkapi poin Accessor.
    public Room getRoom() {
        return room;
    }

    // Method untuk mengambil tanggal check-out.
    // Melengkapi poin Accessor.
    public String getCheckOutDate() {
        return checkOutDate;
    }

    // Method untuk menggabungkan informasi laporan checkout menjadi sebuah string.
    // Melengkapi poin Polymorphism dengan penggunaan overriding method toString().
    @Override
    public String toString() {
        return "Nama Penyewa: " + customer.getName() + 
               ", Nomor Telepon: " + customer.getPhoneNumber() + 
               ", Nama Kamar: " + room.getRoomName() + 
               ", Lokasi: " + room.getLocation() + 
               ", Tanggal Check-out: " + checkOutDate;
    }
}
