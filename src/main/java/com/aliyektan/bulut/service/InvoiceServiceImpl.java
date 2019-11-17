package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.InvoiceDTO;
import com.aliyektan.bulut.entity.Invoice;
import com.aliyektan.bulut.mapper.InvoiceMapper;
import com.aliyektan.bulut.repository.InvoiceRepository;
import com.aliyektan.bulut.service.base.InvoiceService;
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

}
