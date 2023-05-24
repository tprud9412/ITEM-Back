package kit.item.controller;

import com.azure.core.annotation.Get;
import com.azure.core.annotation.Post;
import kit.item.domain.member.Member;
import kit.item.dto.entity.repairShop.EnableTimesDto;
import kit.item.dto.entity.repairShop.RepairShopIdDto;
import kit.item.dto.request.repair.*;
import kit.item.dto.response.repairShop.*;
import kit.item.service.repairShop.RepairShopService;
import kit.item.util.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/repair")
public class RepairShopController {
    private final RepairShopService repairShopService;
    private final TokenProvider tokenProvider;

    @GetMapping("/privateShops")
    public List<ResponsePrivateRepairShopDto> findAllPrivateRepairShops(@RequestHeader HttpHeaders headers) {
        System.out.println("aaaaa" + headers);
        return repairShopService.findAllPrivateRepairShops();
    }

    @GetMapping("/publicShops")
    public List<ResponsePublicRepairShopDto> findAllPublicRepairShops() {
        return repairShopService.findAllPublicRepairShops();
    }

    @GetMapping("/serviceList")
    public List<ResponseServiceDto> getServiceList(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken) {
        Long memberId = Long.valueOf(tokenProvider.getId(tokenProvider.resolveToken(accessToken)));

        return repairShopService.getServiceListByShopID(memberId);
    }

    @PostMapping("/serviceList")
    public boolean createServiceList(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken, @RequestBody RequestServiceCreateInfo requestServiceCreateInfo) {
        Long memberId = Long.valueOf(tokenProvider.getId(tokenProvider.resolveToken(accessToken)));

        return repairShopService.createServiceList(memberId, requestServiceCreateInfo);
    }

    @DeleteMapping("/serviceList")
    public boolean getServiceList(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken, Long serviceId) {
        Long memberId = Long.valueOf(tokenProvider.getId(tokenProvider.resolveToken(accessToken)));

        return repairShopService.deleteServiceByServiceId(memberId, serviceId);
    }

    @GetMapping("/serviceList/info")
    public ResponseServiceDto getServiceInfo(Long serviceId) {
        System.out.println("serviceId = " + serviceId);
        return repairShopService.getServiceInfo(serviceId);
    }

    @PutMapping("/serviceList/info")
    public boolean updateServiceInfo(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken, @RequestBody RequestServiceUpdateInfo requestServiceUpdateInfo) {
        Long memberId = Long.valueOf(tokenProvider.getId(tokenProvider.resolveToken(accessToken)));
        return repairShopService.updateServiceByServiceId(memberId, requestServiceUpdateInfo);
    }

    @PostMapping("/reservation/init")
    public ResponseReservationInitDto initReservation(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken, @RequestBody RepairShopIdDto repairShopId) {
        Long memberId = Long.valueOf(tokenProvider.getId(tokenProvider.resolveToken(accessToken)));
        return repairShopService.reservationInit(memberId, repairShopId.getRepairShopId());
    }

    @GetMapping("/reservation/getEnableTime")
    public EnableTimesDto getEnableTime(@RequestParam Long repairShopId, @RequestParam String date) {
        return repairShopService.getEnableReservationTime(repairShopId, date);
    }

    @PostMapping("/reservation/add")
    public void registReservation(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken,
                                  @RequestParam(value = "productName", required = false, defaultValue = "") String productName,
                                  @RequestParam(value = "prodImg", required = false, defaultValue = "") String prodImg,
                                  @RequestParam(value = "comment", required = false, defaultValue = "") String comment,
                                  @RequestParam(value = "services", required = false) List<String> services,
                                  @RequestParam(value = "rvRequestImgs", required = false) List<MultipartFile> rvRequestImgs,
                                  @RequestParam(value = "date", required = false) LocalDate date,
                                  @RequestParam(value = "time", required = false) String time,
                                  @RequestParam(value = "repairShopId", required = false) Long repairShopId) {
        Long memberId = Long.valueOf(tokenProvider.getId(tokenProvider.resolveToken(accessToken)));

        RequestReservationDto requestReservationDto = RequestReservationDto.builder()
                .productName(productName)
                .prodImg(prodImg)
                .comment(comment)
                .services(services)
                .rvRequestImgs(rvRequestImgs)
                .date(date)
                .time(time)
                .repairShopId(repairShopId)
                .build();
        repairShopService.createReservation(memberId, requestReservationDto);
    }

    @GetMapping("/reservation/history")
    public List<ResponseReservaionHistoryDto> getReservationHistory(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken) {
        Long memberId = Long.valueOf(tokenProvider.getId(tokenProvider.resolveToken(accessToken)));
        return repairShopService.findReservationHistory(memberId);
    }

    @GetMapping("/reservation/history/detail")
    public ResponseReservaionHistoryDto getReservationDetail(@RequestParam Long reservationId) {
        return repairShopService.findReservationHistoryById(reservationId);
    }

    @PostMapping("/reservation/update")
    public void updateReservation(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken,
                                  @RequestParam(value = "productName", required = false, defaultValue = "") String productName,
                                  @RequestParam(value = "prodImg", required = false, defaultValue = "") String prodImg,
                                  @RequestParam(value = "comment", required = false, defaultValue = "") String comment,
                                  @RequestParam(value = "services", required = false) List<String> services,
                                  @RequestParam(value = "rvRequestImgs", required = false) List<MultipartFile> rvRequestImgs,
                                  @RequestParam(value = "date", required = false) LocalDate date,
                                  @RequestParam(value = "time", required = false) String time,
                                  @RequestParam(value = "repairShopId", required = false) Long repairShopId,
                                  @RequestParam(value = "reservationId", required = false) Long reservationId) {
        Long memberId = Long.valueOf(tokenProvider.getId(tokenProvider.resolveToken(accessToken)));

        RequestReservationUpdateDto reservationUpdateDto = RequestReservationUpdateDto.builder()
                .id(reservationId)
                .productName(productName)
                .prodImg(prodImg)
                .comment(comment)
                .services(services)
                .rvRequestImgs(rvRequestImgs)
                .date(date)
                .time(time)
                .repairShopId(repairShopId)
                .build();


        repairShopService.updateReservation(memberId, reservationUpdateDto);
    }

    @GetMapping("/reservation/history/mechanic")
    public List<ResponseReservaionHistoryDto> getReservationHistoryMechanic(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken) {
        Long memberId = Long.valueOf(tokenProvider.getId(tokenProvider.resolveToken(accessToken)));
        return repairShopService.findReservationHistoryMechanic(memberId);
    }

    @PostMapping("/reservation/accept")
    public boolean acceptReservation(@RequestBody RequestReservationStateUpdateDto requestReservationStateUpdateDto) {
        return repairShopService.acceptReservation(requestReservationStateUpdateDto.getReservationId());
    }

    @PostMapping("/reservation/reject")
    public boolean rejectReservation(@RequestBody RequestReservationStateUpdateDto requestReservationStateUpdateDto) {
        return repairShopService.rejectReservation(requestReservationStateUpdateDto.getReservationId());
    }
}