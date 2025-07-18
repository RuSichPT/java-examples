package com.github.rusichpt.mapstruct.example3;

import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Mapper
public abstract class TransactionMapper {
    public TransactionDTO toTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setUuid(transaction.getUuid());
        transactionDTO.setTotalInCents(transaction.getTotal()
                .multiply(new BigDecimal("100")).longValue());
        return transactionDTO;
    }

    // Mapstruct реализует метод, используя toTransactionDTO
    public abstract List<TransactionDTO> toTransactionDTO(Collection<Transaction> transactions);
}
