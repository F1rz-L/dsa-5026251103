package lw01.unguided;

public abstract class WashService implements Billable {
    private String id;
    private int days;

    protected WashService(String id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("days must be positive");
        }
        if (days > 30) {
            throw new IllegalArgumentException("days must not exceed 30");
        }
        this.id = id;
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    @Override 
    public abstract int calculateCharge();

    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("units must be positive");
        }
        if (units > 10) {
            throw new IllegalArgumentException("units must not exceed 10");
        }
        return units * calculateCharge();
    }

    public String label() {
        return "Service";
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}
