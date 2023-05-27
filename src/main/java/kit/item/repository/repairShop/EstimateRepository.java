package kit.item.repository.repairShop;

import kit.item.domain.repair.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstimateRepository extends JpaRepository<Estimate, Long> {

    List<Estimate> findByMemberId(Long memberId);

    List<Estimate> findByRepairShopId(Long repairShopId);
}