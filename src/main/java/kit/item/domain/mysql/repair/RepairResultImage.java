package kit.item.domain.mysql.repair;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Entity(name = "REPAIR_RESULT_IMAGE")
@ToString(callSuper = true)
public class RepairResultImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repair_result_image_id", nullable = false)
    private Long id;

    private String url;
    private String hash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repair_result_id")
    @ToString.Exclude
    private RepairResult repairResult;

    public void setRepairResult(RepairResult repairResult) {
        this.repairResult = repairResult;
    }
}