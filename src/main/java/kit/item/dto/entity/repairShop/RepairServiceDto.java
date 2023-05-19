package kit.item.dto.entity.repairShop;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class RepairServiceDto {
    private Long serviceId;
    private String serviceName;
    private String serviceType;
    private String description;
}
