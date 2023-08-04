package lab.contract.biz.allbuilding.building_register.service;

import lab.contract.biz.allbuilding.building_register.persistence.entity.BuildingRegister;
import lab.contract.biz.allbuilding.building_register.persistence.repository.BuildingRegisterRepository;
import lab.contract.biz.allcontract.contract.persistence.entity.Contract;
import lab.contract.biz.allcontract.contract.persistence.repository.ContractRepository;
import lab.contract.biz.user.persistence.entity.User;
import lab.contract.biz.user.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class BuildingRegisterService {
    private final UserRepository userRepository;
    private final ContractRepository contractRepository;
    private final BuildingRegisterRepository buildingRegisterRepository;

    private static final String UPLOAD_PATH = "C:/contract/getpdf/";

    public Long saveBuildingRegister(Long userId, Long contractId){
        Optional<User> user = userRepository.findById(userId);
        Optional<Contract> contract = contractRepository.findById(contractId);
        BuildingRegister saveBuildingRegister = BuildingRegister.builder()
                .user(user.get())
                .contract(contract.get())
                .build();
        buildingRegisterRepository.save(saveBuildingRegister);

        return saveBuildingRegister.getId();
    }

    public String saveBuildingRegisterPdfFile(MultipartFile pdfFile) throws IOException {
        String pdfFileName = UUID.randomUUID() + "_" + pdfFile.getOriginalFilename();
        File saveFile = new File(UPLOAD_PATH, pdfFileName);
        pdfFile.transferTo(saveFile);
        return pdfFileName;
    }
}
