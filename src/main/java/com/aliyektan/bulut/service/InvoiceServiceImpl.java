package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.InvoiceDTO;
import com.aliyektan.bulut.dto.LicenseNumberDTO;
import com.aliyektan.bulut.entity.Branch;
import com.aliyektan.bulut.entity.Invoice;
import com.aliyektan.bulut.entity.ParkingEvent;
import com.aliyektan.bulut.entity.Pricing;
import com.aliyektan.bulut.mapper.InvoiceMapper;
import com.aliyektan.bulut.repository.InvoiceRepository;
import com.aliyektan.bulut.service.base.InvoiceService;
import com.aliyektan.bulut.service.base.ParkingEventService;
import com.aliyektan.bulut.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService<InvoiceDTO, Integer> {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private ParkingEventService parkingEventService;

    @Autowired
    private UserUtil userUtil;

    @Override
    public InvoiceDTO save(InvoiceDTO dto) {
        Invoice invoice = invoiceMapper.toEntity(dto);
        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public InvoiceDTO findById(Integer id) {

        Invoice invoice = invoiceRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " numaralı fatura bulunamadı !")
                );

        Branch currentBranch = userUtil.getAuthenticatedUser().getRelatedBranch();

        if (currentBranch != null) {
            if (currentBranch.getId().equals(invoice.getParkingEvent().getCurrentBranch().getId())) {
                return invoiceMapper.toDTO(invoice);
            } else {
                throw new EntityNotFoundException(id + " numaralı fatura bulunamadı !");
            }
        }
        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public void deleteById(Integer id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public List<InvoiceDTO> findAll() {
        Branch currentBranch = userUtil.getAuthenticatedUser().getRelatedBranch();

        if (currentBranch != null)
            return invoiceMapper
                    .toDTOList(
                            invoiceRepository
                                    .findByParkingEvent_CurrentBranch_Id(
                                            currentBranch.getId(),
                                            Sort.by(
                                                    Sort.Direction.DESC, "updatedAt"
                                            )
                                    )
                    );

        return invoiceMapper
                .toDTOList(
                        invoiceRepository.findAll(
                                Sort.by(
                                        Sort.Direction.DESC, "updatedAt"
                                )
                        )
                );

    }

    @Override
    public InvoiceDTO bill(LicenseNumberDTO licenseNumberDTO) {

        ParkingEvent endedParkingEvent = parkingEventService.stopParking(licenseNumberDTO);
        List<Pricing> pricings = endedParkingEvent.getCurrentBranch().getPricingList();
        double difStartEnd = endedParkingEvent.getEndDate().getTime() - endedParkingEvent.getStartDate().getTime();
        double diffHours = Math.ceil(difStartEnd / (60 * 60 * 1000));
        double price = 0;
        for (Pricing pricing : pricings) {
            if (pricing.getPricingPeriod().getStart() <= diffHours && pricing.getPricingPeriod().getEnd() >= diffHours) {
                price = pricing.getPrice();
                break;
            }
        }
        Invoice invoice = new Invoice();
        invoice.setParkingEvent(endedParkingEvent);
        invoice.setPaid(false);
        invoice.setPrice(price);

        return invoiceMapper.toDTO(invoiceRepository.save(invoice));
    }

    @Override
    public InvoiceDTO confirm(Integer id) {
        Invoice invoice = invoiceRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " numaralı fatura bulunamadı !")
                );

        invoice.setPaid(true);

        invoice = invoiceRepository.save(invoice);

        return invoiceMapper.toDTO(invoice);
    }

    /* Create Test Data */

//    private static Timestamp  convertStringToTimestamp() {
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");;
//        Timestamp timestamp = null;
//        Date parsedDate;
//        try {
//            parsedDate = dateFormat.parse("2019-12-11 24:00:00");
//            timestamp = new java.sql.Timestamp(parsedDate.getTime());
//
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return timestamp;
//    }


}
