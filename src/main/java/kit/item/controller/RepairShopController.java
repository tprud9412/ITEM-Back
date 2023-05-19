package kit.item.controller;

import kit.item.domain.member.Member;
<<<<<<< HEAD
import kit.item.dto.request.repair.RequestServiceCreateInfo;
import kit.item.dto.request.repair.RequestServiceUpdateInfo;
=======
>>>>>>> parent of 871c74c (23/05/18 1차 it 기기 관리)
import kit.item.dto.response.repairShop.ResponsePrivateRepairShopDto;
import kit.item.dto.response.repairShop.ResponsePublicRepairShopDto;
import kit.item.dto.response.repairShop.ResponseServiceListDto;
import kit.item.service.repairShop.RepairShopService;
import kit.item.util.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/repair")
public class RepairShopController {
    private final RepairShopService repairShopService;
    private final TokenProvider tokenProvider;

    @GetMapping( "/privateShops")
    public List<ResponsePrivateRepairShopDto> findAllPrivateRepairShops(@RequestHeader HttpHeaders headers ) {
        System.out.println("aaaaa" +headers);
        return repairShopService.findAllPrivateRepairShops();
    }

    @GetMapping("/publicShops")
    public List<ResponsePublicRepairShopDto> findAllPublicRepairShops() {
        return repairShopService.findAllPublicRepairShops();
    }

    @GetMapping("/serviceList")
    public List<ResponseServiceListDto> getServiceList(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken){
        Long memberId = Long.valueOf(tokenProvider.getId(tokenProvider.resolveToken(accessToken)));

        return repairShopService.getServiceListByShopID(memberId);
    }

<<<<<<< HEAD
    @PostMapping("/serviceList")
    public boolean createServiceList(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken, @RequestBody RequestServiceCreateInfo requestServiceCreateInfo){
        Long memberId = Long.valueOf(tokenProvider.getId(tokenProvider.resolveToken(accessToken)));

        return repairShopService.createServiceList(memberId, requestServiceCreateInfo);
    }

=======
>>>>>>> parent of 871c74c (23/05/18 1차 it 기기 관리)
    @DeleteMapping("/serviceList")
    public boolean getServiceList(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken, Long serviceId){
        Long memberId = Long.valueOf(tokenProvider.getId(tokenProvider.resolveToken(accessToken)));

        return repairShopService.deleteServiceByServiceId(memberId, serviceId);
    }

    @GetMapping("/serviceList/info")
    public ResponseServiceDto getServiceInfo(Long serviceId){
        System.out.println("serviceId = " + serviceId);
        return repairShopService.getServiceInfo(serviceId);
    }

    @PutMapping("/serviceList/info")
    public boolean updateServiceInfo(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken, @RequestBody RequestServiceUpdateInfo requestServiceUpdateInfo){
        Long memberId = Long.valueOf(tokenProvider.getId(tokenProvider.resolveToken(accessToken)));
        return repairShopService.updateServiceByServiceId(memberId, requestServiceUpdateInfo);
    }


}
