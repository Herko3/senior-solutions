package cars;

import java.time.LocalDate;

public class KmState {

    private LocalDate date;
    private long km;

    public KmState(LocalDate date, long km) {
        this.date = date;
        this.km = km;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getKm() {
        return km;
    }
}
