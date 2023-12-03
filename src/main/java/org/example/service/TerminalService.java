package org.example.service;

import org.example.dto.ResponsDTO;
import org.example.dto.TerminalDTO;
import org.example.enums.Status;
import org.example.repository.TerminalRepository;

import java.util.List;

public class TerminalService {
    TerminalRepository terminalRepository = new TerminalRepository();

    public void creatTerminal(TerminalDTO terminal) {
        ResponsDTO responsDTO = terminalRepository.creatTerminal(terminal);
        if (responsDTO.success()) {
            System.out.println(responsDTO.message());
        } else {
            System.out.println(responsDTO.message());
        }
    }

    public void showTerminalList() {
        List<TerminalDTO> terminalList = terminalRepository.getTerminalList();

        if (terminalList != null) {
            for (TerminalDTO terminalDTO : terminalList) {
                if (terminalDTO.getStatus().equals(Status.ACTIVE)) {
                    System.out.println(terminalDTO);
                } else {
                    System.out.println(terminalDTO);
                }
            }
        } else {
            System.out.println("Any terminals");
        }
    }


    public void updateTerminal(TerminalDTO terminal, String address) {
        ResponsDTO responsDTO = terminalRepository.updateTerminal(terminal, address);
        if (responsDTO.success()) {
            System.out.println(responsDTO.message());
        } else {
            System.out.println(responsDTO.message());
        }
    }

    public void changeTerminalStatusByAdmin(String terminalCode, String newTerminalStatus) {
        if (newTerminalStatus.equals("ACTIVE") || newTerminalStatus.equals("NO_ACTIVE") || newTerminalStatus.equals("BLOCKED")) {
            List<TerminalDTO> terminalList = terminalRepository.getTerminalList();
            ResponsDTO result = null;
            if (terminalList == null) {
                System.out.println("Terminal if not exist!!!");
                return;
            } else {
                for (TerminalDTO terminalDTO : terminalList) {
                    if (terminalDTO.getCode().equals(terminalCode)) {
                        result = terminalRepository.changeTerminalStatusByAdmin(terminalCode, newTerminalStatus);
                    }
                }

                if (result == null) {
                    System.out.println(" Wrong terminal code!!! ");
                } else if (result.success()) {
                    System.out.println(result.message());
                } else {
                    System.out.println(result.message());
                }
            }
        }else {
            System.out.println("Status (ACTIVE or NO_ACTIVE or BLOCKED) be written in format!!!");
        }


    }

    public void deleteTerminal(String terminalCode) {
        List<TerminalDTO> terminalList = terminalRepository.getTerminalList();
        ResponsDTO result = null;
        if (terminalList == null) {
            System.out.println("Terminal if not exist!!!");
        } else {
            for (TerminalDTO terminalDTO : terminalList) {
                if (terminalDTO.getCode().equals(terminalCode)) {
                    result = terminalRepository.deleteTerminal(terminalCode);
                }
            }

            if (result == null) {
                System.out.println(" Wrong terminal code!!! ");
            } else if (result.success()) {
                System.out.println(result.message());
            } else {
                System.out.println(result.message());
            }
        }

    }

    public boolean chackTerminalCode(String terminalCode) {
        List<TerminalDTO> terminalList = terminalRepository.getTerminalList();
        for (TerminalDTO terminalDTO : terminalList) {
            if (terminalDTO.getCode().equals(terminalCode)&&terminalDTO.getStatus().equals(Status.ACTIVE)){
                return true;
            }
        }
        return false;
    }

}
