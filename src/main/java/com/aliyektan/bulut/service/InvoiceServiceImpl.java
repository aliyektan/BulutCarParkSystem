package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.InvoiceDTO;
import com.aliyektan.bulut.dto.LicenseNumberDTO;
import com.aliyektan.bulut.entity.Invoice;
import com.aliyektan.bulut.entity.ParkingEvent;
import com.aliyektan.bulut.entity.Pricing;
import com.aliyektan.bulut.mapper.InvoiceMapper;
import com.aliyektan.bulut.repository.InvoiceRepository;
import com.aliyektan.bulut.service.base.InvoiceService;
import com.aliyektan.bulut.service.base.ParkingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService<InvoiceDTO, Integer> {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private ParkingEventService parkingEventService;

    @Override
    public InvoiceDTO save(InvoiceDTO dto) {
        Invoice invoice = invoiceMapper.toEntity(dto);
        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public InvoiceDTO findById(Integer id) {
        return invoiceMapper.toDTO(invoiceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " numaralı fatura bulunamadı !")));
    }

    @Override
    public void deleteById(Integer id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public List<InvoiceDTO> findAll() {
        return invoiceMapper.toDTOList(invoiceRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt")));
    }

    @Override
    public InvoiceDTO bill(LicenseNumberDTO licenseNumberDTO) {

        ParkingEvent endedParkingEvent = parkingEventService.stopParking(licenseNumberDTO);
        List<Pricing> pricings = endedParkingEvent.getCurrentBranch().getPricingList();
        double difStartEnd = endedParkingEvent.getEndDate().getTime() - endedParkingEvent.getStartDate().getTime();
        double diffHours = Math.ceil(difStartEnd / (60 * 60 * 1000));
        double price = 0;
        for (Pricing pricing : pricings) {
            if(pricing.getPricingPeriod().getStart() <= diffHours && pricing.getPricingPeriod().getEnd() >= diffHours ){
                price = pricing.getPrice();
                break;
            }
        }
        Invoice invoice = new Invoice();
        invoice.setParkingEvent(endedParkingEvent);
        invoice.setPaid(true);
        invoice.setPrice(price);

        return invoiceMapper.toDTO(invoiceRepository.save(invoice));
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
