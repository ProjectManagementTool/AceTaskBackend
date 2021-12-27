package codes.laser.ppmtool.pojo;

import codes.laser.ppmtool.model.Designation;
import lombok.Data;

@Data
public class ProfileUpdatePojo {

    private Long userId;

    private double experienceInYear;

    private Designation designation;

    private String skillsDescription;
}
