package com.ordering.system.paymentdataaccess.creditentry.mapper;

import com.ordering.system.commondomain.valueobject.CustomerId;
import com.ordering.system.commondomain.valueobject.Money;
import com.ordering.system.paymentdataaccess.creditentry.entity.CreditEntryEntity;
import com.ordering.system.paymentdomaincore.entity.CreditEntry;
import com.ordering.system.paymentdomaincore.valueobject.CreditEntryId;
import org.springframework.stereotype.Component;

@Component
public class CreditEntryDataAccessMapper {

    public CreditEntry creditEntryEntityToCreditEntry(CreditEntryEntity creditEntryEntity) {
        return CreditEntry.builder()
                .creditEntryId(new CreditEntryId(creditEntryEntity.getId()))
                .customerId(new CustomerId(creditEntryEntity.getCustomerId()))
                .totalCreditAmount(new Money(creditEntryEntity.getTotalCreditAmount()))
                .build();
    }

    public CreditEntryEntity creditEntryToCreditEntryEntity(CreditEntry creditEntry) {
        return CreditEntryEntity.builder()
                .id(creditEntry.getId().getValue())
                .customerId(creditEntry.getCustomerId().getValue())
                .totalCreditAmount(creditEntry.getTotalCreditAmount().getAmount())
                .build();
    }

}
