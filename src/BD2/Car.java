package BD2;

class Car {
    String licensePlate;

    Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return licensePlate;
    }
}