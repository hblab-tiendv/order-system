package com.ordering.system.paymentdataaccess.credithistory.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ordering.system.commondomain.valueobject.CustomerId;
import com.ordering.system.paymentapplicationservice.ports.output.repository.CreditHistoryRepository;
import com.ordering.system.paymentdataaccess.credithistory.entity.CreditHistoryEntity;
import com.ordering.system.paymentdataaccess.credithistory.mapper.CreditHistoryDataAccessMapper;
import com.ordering.system.paymentdataaccess.credithistory.repository.CreditHistoryJpaRepository;
import com.ordering.system.paymentdomaincore.entity.CreditHistory;
import org.springframework.stereotype.Component;

@Component
public class CreditHistoryRepositoryImpl implements CreditHistoryRepository {

    private final CreditHistoryJpaRepository creditHistoryJpaRepository;
    private final CreditHistoryDataAccessMapper creditHistoryDataAccessMapper;

    public CreditHistoryRepositoryImpl(CreditHistoryJpaRepository creditHistoryJpaRepository,
                                       CreditHistoryDataAccessMapper creditHistoryDataAccessMapper) {
        this.creditHistoryJpaRepository = creditHistoryJpaRepository;
        this.creditHistoryDataAccessMapper = creditHistoryDataAccessMapper;
    }

    @Override
    public CreditHistory save(CreditHistory creditHistory) {
        return creditHistoryDataAccessMapper.creditHistoryEntityToCreditHistory(creditHistoryJpaRepository
                .save(creditHistoryDataAccessMapper.creditHistoryToCreditHistoryEntity(creditHistory)));
    }

    @Override
    public Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId) {
        Optional<List<CreditHistoryEntity>> creditHistory =
                creditHistoryJpaRepository.findByCustomerId(customerId.getValue());
        return creditHistory
                .map(creditHistoryList ->
                        creditHistoryList.stream()
                                .map(creditHistoryDataAccessMapper::creditHistoryEntityToCreditHistory)
                                .collect(Collectors.toList()));
    }
}
