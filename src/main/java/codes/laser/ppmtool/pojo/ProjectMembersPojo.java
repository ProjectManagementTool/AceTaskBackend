package codes.laser.ppmtool.pojo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ProjectMembersPojo {

    private Integer id;

    @Column(unique = true)
    private Long userid;

    private Long projectid;
}
