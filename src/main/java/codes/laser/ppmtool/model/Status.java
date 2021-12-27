package codes.laser.ppmtool.model;

public enum Status {

    TO_DO(0),
    PROGRESS(1),
    COMPLETED(2);

    private Integer value;

    Status(Integer value) {
        this.value = value;
    }
}
