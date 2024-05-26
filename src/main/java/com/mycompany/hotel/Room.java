package com.mycompany.hotel;

// Class Room merupakan representasi dari kamar hotel.
// Ini adalah contoh dari pembuatan kelas.

public class Room {
    // Atribut-atribut yang dimiliki oleh kelas Room.
    private String location; // Atribut lokasi kamar.
    private String roomName; // Atribut nama kamar.
    private double pricePerNight; // Atribut harga kamar per malam.
    private String roomType; // Atribut tipe kamar.
    private boolean isReady; // Atribut status kamar (siap/tidak siap).
    private Customer bookedBy; // Atribut objek Customer yang menyewa kamar.

    // Constructor untuk membuat objek Room baru.
    // Melengkapi point Constructor.
    public Room(String location, String roomName, double pricePerNight, String roomType) {
        this.location = location;
        this.roomName = roomName;
        this.pricePerNight = pricePerNight;
        this.roomType = roomType;
        this.isReady = true; // Saat dibuat, kamar dianggap siap.
    }

    // Accessor untuk mengembalikan nilai dari location.
    // Melengkapi point Accessor.
    public String getLocation() {
        return location;
    }

    // Accessor untuk mengembalikan nilai dari roomName.
    // Melengkapi point Accessor.
    public String getRoomName() {
        return roomName;
    }

    // Accessor untuk mengembalikan nilai dari pricePerNight.
    // Melengkapi point Accessor.
    public double getPricePerNight() {
        return pricePerNight;
    }

    // Accessor untuk mengembalikan nilai dari roomType.
    // Melengkapi point Accessor.
    public String getRoomType() {
        return roomType;
    }

    // Accessor untuk mengembalikan nilai dari isReady.
    // Melengkapi point Accessor.
    public boolean isReady() {
        return isReady;
    }

    // Mutator untuk mengubah status kamar menjadi tidak siap dan menyimpan informasi pemesan.
    // Melengkapi point Mutator.
    public void bookRoom(Customer customer) {
        this.bookedBy = customer;
        this.isReady = false;
    }

    // Mutator untuk mengubah status kamar menjadi siap dan menghapus informasi pemesan.
    // Melengkapi point Mutator.
    public void releaseRoom() {
        this.bookedBy = null;
        this.isReady = true;
    }

    // Accessor untuk mengembalikan objek Customer yang menyewa kamar.
    // Melengkapi point Accessor.
    public Customer getBookedBy() {
        return bookedBy;
    }
}

