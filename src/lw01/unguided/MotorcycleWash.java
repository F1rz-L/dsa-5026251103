package lw01.unguided;

public class MotorcycleWash extends WashService {
    private int units;
    public MotorcycleWash(String id, int days, int units) {
        super(id, days);
        this.units = units;
    }

    @Override
    public int calculateCharge() {
        return ((getDays() * 15000) + 5000) * units;
    }

    @Override
    public String label() {
        return "Motorcycle";
    }
}
