package org.example.service;

import org.example.dto.CardDTO;
import org.example.dto.ResponsDTO;
import org.example.dto.TerminalDTO;
import org.example.dto.TransactionDTO;
import org.example.enums.Status;
import org.example.enums.TransactionType;
import org.example.repository.CardRepository;
import org.example.repository.TerminalRepository;
import org.example.repository.TransactionRepository;

import java.util.List;

public class TransactionService {
    CardRepository cardRepository = new CardRepository();
    CardService cardService = new CardService();
    TerminalRepository terminalRepository = new TerminalRepository();
    TerminalService terminalService = new TerminalService();
    TransactionRepository transactionRepository = new TransactionRepository();

    public void makePayment(String cardNumber, String terminalCode, double amount, TransactionType type) {
        List<CardDTO> cardDTOList = cardRepository.getCardList();
        List<TerminalDTO> terminalDTOS = terminalRepository.getTerminalList();
        ResponsDTO resultTerminal = null;
        boolean terminalChecking = terminalService.chackTerminalCode(terminalCode);
        boolean checkCardCompany = cardService.chackCardCompany();
        ResponsDTO resultCard = null;
        if (cardDTOList == null) {
            System.out.println("Card if not exist!!!");
        } else if (terminalDTOS == null) {
            System.out.println("Terminal if not exist!!!");
        } else if (amount < 0) {
            System.out.println("Amount entered error!!!");
        } else {
            for (CardDTO cardDTO : cardDTOList) {
                if (cardDTO.getNumber().equals(cardNumber)
                        && terminalChecking
                        && cardDTO.getStatus().equals(Status.ACTIVE) && checkCardCompany) {
                    resultTerminal = transactionRepository.creadTransaction(cardNumber, terminalCode, amount, type);
                    resultCard = cardRepository.updateCardBalance(cardNumber, amount);
                    cardRepository.updateCardCompany(amount);
                }
            }

            if (!checkCardCompany) {
                System.out.println("Company card not found!!!");
            } else if (!terminalChecking) {
                System.out.println("Terminal not found!!! ");
            } else if (resultCard == null) {
                System.out.println("Card not found!!! ");
            } else if (resultTerminal.success()) {
                System.out.println(resultTerminal.message());
            } else {
                System.out.println(resultTerminal.message());
            }

        }


    }

    public boolean getTransaction(String cardNumber) {
        List<TransactionDTO> transactionList = transactionRepository.getTransactionList(cardNumber);
        return transactionList.isEmpty() ;
    }

    public boolean getTransactionToday() {
        List<TransactionDTO> transactionDTOS = transactionRepository.gettransactionToday();
        return transactionDTOS.isEmpty() ;

    }

    public boolean transactionList() {
        List<TransactionDTO> allTransactions = transactionRepository.getAllTransactions();
        return allTransactions.isEmpty();
    }
}
