package lw01.unguided;

public class CarWash extends WashService {
    private int total;
    private int units;

    public CarWash(String id, int days, int units) {
        super(id, days);
        this.units = units;
    }

    @Override
    public int calculateCharge() {
        if (getDays() >= 3) {
            total = ((3 * 35000) + (getDays() - 3) * 25000 + 15000);
        } else {
            total = getDays() * 35000;
        }

        return total * units;
    }

    @Override
    public String label() {
        return "Car";
    }
}
