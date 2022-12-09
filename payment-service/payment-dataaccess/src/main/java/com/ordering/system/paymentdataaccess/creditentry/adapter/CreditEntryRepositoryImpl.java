package com.ordering.system.paymentdataaccess.creditentry.adapter;

import java.util.Optional;

import com.ordering.system.commondomain.valueobject.CustomerId;
import com.ordering.system.paymentapplicationservice.ports.output.repository.CreditEntryRepository;
import com.ordering.system.paymentdataaccess.creditentry.mapper.CreditEntryDataAccessMapper;
import com.ordering.system.paymentdataaccess.creditentry.repository.CreditEntryJpaRepository;
import com.ordering.system.paymentdomaincore.entity.CreditEntry;
import org.springframework.stereotype.Component;

@Component
public class CreditEntryRepositoryImpl implements CreditEntryRepository {

    private final CreditEntryJpaRepository creditEntryJpaRepository;
    private final CreditEntryDataAccessMapper creditEntryDataAccessMapper;

    public CreditEntryRepositoryImpl(CreditEntryJpaRepository creditEntryJpaRepository,
                                     CreditEntryDataAccessMapper creditEntryDataAccessMapper) {
        this.creditEntryJpaRepository = creditEntryJpaRepository;
        this.creditEntryDataAccessMapper = creditEntryDataAccessMapper;
    }

    @Override
    public CreditEntry save(CreditEntry creditEntry) {
        return creditEntryDataAccessMapper
                .creditEntryEntityToCreditEntry(creditEntryJpaRepository
                        .save(creditEntryDataAccessMapper.creditEntryToCreditEntryEntity(creditEntry)));
    }

    @Override
    public Optional<CreditEntry> findByCustomerId(CustomerId customerId) {
        return creditEntryJpaRepository
                .findByCustomerId(customerId.getValue())
                .map(creditEntryDataAccessMapper::creditEntryEntityToCreditEntry);
    }
}
