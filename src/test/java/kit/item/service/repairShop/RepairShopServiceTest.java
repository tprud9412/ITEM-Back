package kit.item.service.repairShop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class RepairShopServiceTest {

    @Autowired
    RepairShopService repairShopService;

    @Test
    void 모든_사설_정비소_조회() {

        System.out.println(repairShopService.findAllPrivateRepairShops());

    }
}