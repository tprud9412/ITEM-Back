package kit.item.service.repairShop;

import kit.item.domain.member.RepairShop;
import kit.item.domain.repair.OfficialRepairShop;
import kit.item.domain.repair.RepairService;
import kit.item.dto.entity.repairShop.RepairServiceDto;
<<<<<<< HEAD
import kit.item.dto.request.repair.RequestServiceCreateInfo;
import kit.item.dto.request.repair.RequestServiceUpdateInfo;
import kit.item.dto.response.repairShop.ResponsePrivateRepairShopDto;
import kit.item.dto.response.repairShop.ResponsePublicRepairShopDto;
import kit.item.dto.response.repairShop.ResponseServiceDto;
=======
import kit.item.dto.response.repairShop.ResponsePrivateRepairShopDto;
import kit.item.dto.response.repairShop.ResponsePublicRepairShopDto;
import kit.item.dto.response.repairShop.ResponseServiceListDto;
import kit.item.enums.ServiceType;
>>>>>>> parent of 871c74c (23/05/18 1차 it 기기 관리)
import kit.item.repository.repairShop.OfficialRepairShopRepository;
import kit.item.repository.repairShop.RepairShopRepository;
import kit.item.repository.repairShop.RepairShopServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairShopService {
    private final RepairShopServiceRepository repairShopServiceRepository;
    private final RepairShopRepository repairShopRepository;
    private final OfficialRepairShopRepository officialRepairShopRepository;

    public List<ResponsePrivateRepairShopDto> findAllPrivateRepairShops() {

        List<ResponsePrivateRepairShopDto> responsePrivateRepairShopDtos = new ArrayList<>();
        List<RepairShop> repairShops = repairShopRepository.findAll();

        repairShops.stream().forEach(repairShop -> {

            List<RepairServiceDto> repairServiceDtos = new ArrayList<>();
            List<RepairService> servicesByRepairShopId = repairShopServiceRepository.findByRepairShopId(repairShop.getId());
            servicesByRepairShopId.stream().forEach(repairService -> {
                repairServiceDtos.add(
                        RepairServiceDto.builder()
                                .serviceId(repairService.getId())
                                .serviceName(repairService.getServiceName())
                                .serviceType(repairService.getServiceType())
                                .description(repairService.getDescription())
                                .build());
            });

            responsePrivateRepairShopDtos.add(ResponsePrivateRepairShopDto.builder()
                    .repairShopId(repairShop.getId())
                    .shopName(repairShop.getShopName())
                    .shopAddress(repairShop.getAddress())
                    .shopPhoneNumber(repairShop.getShopPhoneNumber())
                    .description(repairShop.getDescription())
                    .services(repairServiceDtos)
                    .build());

        });
        return responsePrivateRepairShopDtos;
    }

    public List<ResponseServiceListDto> getServiceListByShopID(Long shopId){

        List<RepairService> serviceList = repairShopServiceRepository.findByRepairShopId(shopId);

        List<ResponseServiceListDto> responseServiceListDtos = new ArrayList<>();

        serviceList.stream().forEach(repairService -> {
            responseServiceListDtos.add(ResponseServiceListDto.builder()
                    .serviceId(repairService.getId())
                    .serviceName(repairService.getServiceName())
                    .serviceType(String.valueOf(repairService.getServiceType()))
                    .description(repairService.getDescription())
                    .build());

        });
        return responseServiceListDtos;
    }

    public boolean deleteServiceByServiceId(Long memberId, Long serviceId){

        boolean isExist = repairShopServiceRepository.existsByMemberIdAndServiceId(memberId, serviceId);

        if(isExist){
            repairShopServiceRepository.deleteById(serviceId);
            return true;
        }
        return false;
    }

<<<<<<< HEAD
    public boolean updateServiceByServiceId(Long memberId, RequestServiceUpdateInfo requestServiceUpdateInfo) {

        boolean isExist = repairShopServiceRepository.existsByMemberIdAndServiceId(memberId, requestServiceUpdateInfo.getServiceId());

        if(isExist && !(requestServiceUpdateInfo.getServiceName() == ""
                || requestServiceUpdateInfo.getServiceName() == null
                || requestServiceUpdateInfo.getDescription() == ""
                || requestServiceUpdateInfo.getDescription() == null)){
=======
    public List<ResponsePublicRepairShopDto> findAllPublicRepairShops() {

        List<ResponsePublicRepairShopDto> responsePublicRepairShopDtos = new ArrayList<>();
        List<OfficialRepairShop> officialRepairShops = officialRepairShopRepository.findAll();

        officialRepairShops.stream().forEach(officialRepairShop -> {
>>>>>>> parent of 871c74c (23/05/18 1차 it 기기 관리)

            responsePublicRepairShopDtos.add(
                    ResponsePublicRepairShopDto.builder()
                            .officeShopId(officialRepairShop.getId())
                            .shopName(officialRepairShop.getName())
                            .shopAddress(officialRepairShop.getAddress())
                            .shopPhoneNumber(officialRepairShop.getPhoneNumber())
                            .description(officialRepairShop.getDescription())
                            .build()
            );
<<<<<<< HEAD
            return true;
        }
        return false;
    }

    public boolean createServiceList(Long memberId, RequestServiceCreateInfo requestServiceCreateInfo) {
        Optional<RepairShop> repairShop = repairShopRepository.findById(memberId);

        if(requestServiceCreateInfo.getServiceName() == ""
                || requestServiceCreateInfo.getServiceName() == null
                || requestServiceCreateInfo.getDescription() == ""
                || requestServiceCreateInfo.getDescription() == null)
            return false;

        if(repairShop.isPresent()){
            RepairService repairService = RepairService.builder()
                            .serviceType(requestServiceCreateInfo.getServiceType())
                            .serviceName(requestServiceCreateInfo.getServiceName())
                            .description(requestServiceCreateInfo.getDescription())
                            .repairShop(repairShop.get()).build();

            repairShopServiceRepository.save(repairService);

            return true;

        }else
            return false;
=======
        });

        return responsePublicRepairShopDtos;
>>>>>>> parent of 871c74c (23/05/18 1차 it 기기 관리)
    }
}
