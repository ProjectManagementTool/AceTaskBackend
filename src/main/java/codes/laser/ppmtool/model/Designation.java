package codes.laser.ppmtool.model;

public enum Designation {
    BACKEND_DEVELOPER(0),
    FRONTEND_DEVELOPER(1),
    FULLSTACK_DEVELOPER(2);

    private Integer value;

    Designation(Integer value) {
        this.value = value;
    }
}

