package com.css.autocsfinal.Approval.service;

import com.css.autocsfinal.Approval.dto.AppDeptResult;
import com.css.autocsfinal.Approval.dto.BusinessDTO;
import com.css.autocsfinal.Approval.dto.PurchaseListDTO;
import com.css.autocsfinal.Approval.dto.TrafficListDTO;
import com.css.autocsfinal.Approval.entity.*;
import com.css.autocsfinal.Approval.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApprovalService {

    private final ApprovalRepository approvalRepository;
    private final AppEmplRepository appEmplRepository;
    private final DocumentRepository documentRepository;
    private final PurchaseRepository purchaseRepository;
    private final DocumentFileRepository documentFileRepository;
    private final ReceiveRepository receiveRepository;
    private final ApproverRepository approverRepository;
    private final ApprovalListRepository approvalListRepository;
    private final TrafficRepository trafficRepository;
    private final BusinessRepository businessRepository;

    public List<AppDeptResult> findDept() {

        log.info("[ApprovalService] findDept 접근 완료");

        List<AppDeptEntity> deptList = approvalRepository.findByUpperDeptCode("B1");

        List<AppDeptResult> results = new ArrayList<>();

        for(int i = 0; i < deptList.size(); i++) {
            AppDeptResult result = new AppDeptResult();
            result.setId(i + 1);
            result.setParent(0);
            result.setText(deptList.get(i).getName());

            results.add(result);
        }

        List<AppEmplEntity> emplList = appEmplRepository.findAll();

        for(int i = 0; i < emplList.size(); i++) {
            AppDeptResult result = new AppDeptResult();

            switch(emplList.get(i).getDeptCode()) {
                case "H1" : result.setId(deptList.size() + i + 1);
                            result.setParent(1);
                            result.setText(emplList.get(i).getName() + " " + emplList.get(i).getPosition().getName());
                            result.setPosition(emplList.get(i).getPosition().getName());
                            result.setEmpNo(emplList.get(i).getEmployeeNo());
                            result.setName(emplList.get(i).getName());
                            results.add(result); break;
                case "F1" : result.setId(deptList.size() + i + 1);
                            result.setParent(2);
                            result.setText(emplList.get(i).getName() + " " + emplList.get(i).getPosition().getName());
                            result.setPosition(emplList.get(i).getPosition().getName());
                            result.setEmpNo(emplList.get(i).getEmployeeNo());
                            result.setName(emplList.get(i).getName());
                            results.add(result); break;
                case "M1" : result.setId(deptList.size() + i + 1);
                            result.setParent(3);
                            result.setText(emplList.get(i).getName() + " " + emplList.get(i).getPosition().getName());
                            result.setPosition(emplList.get(i).getPosition().getName());
                            result.setEmpNo(emplList.get(i).getEmployeeNo());
                            result.setName(emplList.get(i).getName());
                            results.add(result); break;
                case "M2" : result.setId(deptList.size() + i + 1);
                            result.setParent(4);
                            result.setText(emplList.get(i).getName() + " " + emplList.get(i).getPosition().getName());
                            result.setPosition(emplList.get(i).getPosition().getName());
                            result.setEmpNo(emplList.get(i).getEmployeeNo());
                            result.setName(emplList.get(i).getName());
                            results.add(result); break;
                case "S1" : result.setId(deptList.size() + i + 1);
                            result.setParent(5);
                            result.setText(emplList.get(i).getName() + " " + emplList.get(i).getPosition().getName());
                            result.setPosition(emplList.get(i).getPosition().getName());
                            result.setEmpNo(emplList.get(i).getEmployeeNo());
                            result.setName(emplList.get(i).getName());
                            results.add(result); break;
                case "S2" : result.setId(deptList.size() + i + 1);
                            result.setParent(6);
                            result.setText(emplList.get(i).getName() + " " + emplList.get(i).getPosition().getName());
                            result.setPosition(emplList.get(i).getPosition().getName());
                            result.setEmpNo(emplList.get(i).getEmployeeNo());
                            result.setName(emplList.get(i).getName());
                            results.add(result); break;
            }
        }

        return results;
    }

    /* 구매요청 */
    @Transactional
    public void registPurchase(PurchaseListDTO purchaseList, List<MultipartFile> files) {

        log.info("[ApprovalService] registPurchase purchaseList : {}", purchaseList);

        Date date = new Date();

        /* 문서테이블에 insert */
        DocumentEntity documentEntity =
                documentRepository.save(new DocumentEntity(purchaseList.getEmpNo(),date,"구매요청"));

        int documentCode = documentEntity.getDocumentCode();;

        /* 구매요청 테이블에 값 하나하나 빼서 insert*/
        for(int i = 0; i < purchaseList.getPrice().size(); i++) {

            PurchaseEntity purchaseEntity = new PurchaseEntity();

            purchaseEntity.setDocumentCode(documentCode);
            purchaseEntity.setPurchaseTitle(purchaseList.getDocTitle());
            purchaseEntity.setProductName(purchaseList.getProductName().get(i));
            purchaseEntity.setStandard(purchaseList.getProductSize().get(i));
            purchaseEntity.setAmount(purchaseList.getAmount().get(i));
            purchaseEntity.setUnitPrice(purchaseList.getPrice().get(i));

            if(purchaseList.getNote().size() != 0) {

                if(purchaseList.getNote().get(i) != null) {

                    purchaseEntity.setRemarks(purchaseList.getNote().get(i));
                }
            }

            purchaseRepository.save(purchaseEntity);
        }

        /* 파일 저장 경로 만들어서 파일 테이블에 insert */

        String root = "C:\\dev\\approvalFile\\";
        String mainFilePath = root + "purchase";

        File mkdir = new File(mainFilePath);
        if(!mkdir.exists()) {
            mkdir.mkdirs();
        }

        for(int i = 0; i < files.size(); i++) {
            String originName = files.get(i).getOriginalFilename();
            String ext = originName.substring(originName.lastIndexOf("."));
            String modifyName = UUID.randomUUID().toString().replace("-","") + ext;

            DocumentFileEntity documentFileEntity = new DocumentFileEntity();
            documentFileEntity.setOriginName(originName);
            documentFileEntity.setModifyName(modifyName);
            documentFileEntity.setFilePath(mainFilePath);
            documentFileEntity.setDocumentCode(documentCode);

            try {
                files.get(i).transferTo(new File(mainFilePath + "\\" + modifyName));
                documentFileRepository.saveAndFlush(documentFileEntity);
            } catch (IOException e) {
                new File(mainFilePath + "\\" + modifyName).delete();
                documentFileRepository.delete(documentFileEntity);
            }
        }

        /* 수신자 테이블 insert */
        for(int i = 0; i < purchaseList.getReceive().size(); i++) {

            ReceiverEntity receiverEntity = receiveRepository.save(new ReceiverEntity(
                    purchaseList.getReceive().get(i), documentCode, "대기중"

            ));

            log.info("[ApprovalService] 수신자 테이블 수신자 : {} ", purchaseList.getReceive().get(i));
        }

        /* 결재 테이블 insert */
        for(int i = 0; i < purchaseList.getAllow().size(); i++) {

            ApprovalEntity approvalEntity = approverRepository.save(new ApprovalEntity(
                    documentCode, purchaseList.getAllow().get(i)
            ));

            approvalListRepository.save(new ApprovalListEntity(
                    "결재요청", date, "", approvalEntity.getApprovalCode()
            ));
        }
    }

    /* 여비정산 */
    @Transactional
    public void registTraffic(TrafficListDTO trafficList, List<MultipartFile> files) {

        Date date = new Date();

        /* 문서 테이블 */
        DocumentEntity documentEntity = documentRepository.save(new DocumentEntity(
            trafficList.getEmpNo(), date, "여비정산"
        ));

        int documentCode = documentEntity.getDocumentCode();

        /* 여비정산 테이블 */
        for(int i = 0; i < trafficList.getTrafficDate().size(); i++) {

            TrafficEntity trafficEntity = new TrafficEntity();
            trafficEntity.setTrafficDate(trafficList.getTrafficDate().get(i));
            trafficEntity.setTime(trafficList.getTrafficTime().get(i));
            trafficEntity.setStartPoint(trafficList.getFrom().get(i));
            trafficEntity.setDestination(trafficList.getTo().get(i));
            trafficEntity.setDistance(trafficList.getDistance().get(i));
            trafficEntity.setPrice(trafficList.getTrafficPrice().get(i));
            trafficEntity.setVehicle(trafficList.getVehicle().get(i));
            trafficEntity.setBusiness(trafficList.getBusiness().get(i));
            trafficEntity.setDocumentCode(documentCode);

            trafficRepository.save(trafficEntity);
        }

        /* 결재 테이블 */
        for(int i = 0; i < trafficList.getAllow().size(); i++) {

            ApprovalEntity approvalEntity = approverRepository.save(new ApprovalEntity(
                    documentCode, trafficList.getAllow().get(i)
            ));

            approvalListRepository.save(new ApprovalListEntity(
                    "결재요청", date, "", approvalEntity.getApprovalCode()
            ));
        }

        /* 수신자 */
        for(int i = 0; i < trafficList.getReceive().size(); i++) {

            receiveRepository.save(new ReceiverEntity(
                    trafficList.getReceive().get(i), documentCode, "대기중"
            ));
        }

        /* 파일 저장 경로 만들어서 파일 테이블에 insert */
        String root = "C:\\dev\\approvalFile\\";
        String mainFilePath = root + "traffic";

        File mkdir = new File(mainFilePath);
        if(!mkdir.exists()) {
            mkdir.mkdirs();
        }

        for(int i = 0; i < files.size(); i++) {
            String originName = files.get(i).getOriginalFilename();
            String ext = originName.substring(originName.lastIndexOf("."));
            String modifyName = UUID.randomUUID().toString().replace("-","") + ext;

            DocumentFileEntity documentFileEntity = new DocumentFileEntity();
            documentFileEntity.setOriginName(originName);
            documentFileEntity.setModifyName(modifyName);
            documentFileEntity.setFilePath(mainFilePath);
            documentFileEntity.setDocumentCode(documentCode);

            try {
                files.get(i).transferTo(new File(mainFilePath + "\\" + modifyName));
                documentFileRepository.saveAndFlush(documentFileEntity);
            } catch (IOException e) {
                new File(mainFilePath + "\\" + modifyName).delete();
                documentFileRepository.delete(documentFileEntity);
            }
        }
    }

    /* 업무보고 */
    @Transactional
    public void registBusiness(BusinessDTO business, List<MultipartFile> files) {

        Date date = new Date();

        /* 문서 테이블 */
        DocumentEntity documentEntity = documentRepository.save(new DocumentEntity(
                business.getEmpNo(), date, "업무보고"
        ));

        int documentCode = documentEntity.getDocumentCode();

        /* 업무보고 테이블 */
        BusinessEntity businessEntity = new BusinessEntity();
        businessEntity.setBusinessTitle(business.getBusinessTitle());
        businessEntity.setBusinessContent(business.getBusiness());
        businessEntity.setRemarks(business.getBusinessNote());
        businessEntity.setDocumentCode(documentCode);

        businessRepository.save(businessEntity);

        /* 결재 테이블 */
        for(int i = 0; i < business.getAllow().size(); i++) {

            ApprovalEntity approvalEntity = approverRepository.save(new ApprovalEntity(
                    documentCode, business.getAllow().get(i)
            ));

            approvalListRepository.save(new ApprovalListEntity(
                    "결재요청", date, "", approvalEntity.getApprovalCode()
            ));
        }

        /* 수신자 */
        for(int i = 0; i < business.getReceive().size(); i++) {

            receiveRepository.save(new ReceiverEntity(
                    business.getReceive().get(i), documentCode, "대기중"
            ));
        }

        /* 파일 저장 경로 만들어서 파일 테이블에 insert */
        String root = "C:\\dev\\approvalFile\\";
        String mainFilePath = root + "business";

        File mkdir = new File(mainFilePath);
        if(!mkdir.exists()) {
            mkdir.mkdirs();
        }

        for(int i = 0; i < files.size(); i++) {
            String originName = files.get(i).getOriginalFilename();
            String ext = originName.substring(originName.lastIndexOf("."));
            String modifyName = UUID.randomUUID().toString().replace("-","") + ext;

            DocumentFileEntity documentFileEntity = new DocumentFileEntity();
            documentFileEntity.setOriginName(originName);
            documentFileEntity.setModifyName(modifyName);
            documentFileEntity.setFilePath(mainFilePath);
            documentFileEntity.setDocumentCode(documentCode);

            try {
                files.get(i).transferTo(new File(mainFilePath + "\\" + modifyName));
                documentFileRepository.saveAndFlush(documentFileEntity);
            } catch (IOException e) {
                new File(mainFilePath + "\\" + modifyName).delete();
                documentFileRepository.delete(documentFileEntity);
            }
        }
    }
}
