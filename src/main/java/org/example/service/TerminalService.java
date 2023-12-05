package org.example.service;

import lombok.Setter;
import org.example.controller.Appl;
import org.example.dto.ResponsDTO;
import org.example.dto.TerminalDTO;
import org.example.enums.Status;
import org.example.repository.ProfileRepository;
import org.example.repository.TerminalRepository;

import java.util.List;
@Setter
public class TerminalService {
//    TerminalRepository terminalRepository;//spring da tajriba oxshamadi

    TerminalRepository terminalRepository;

    public void creatTerminal(TerminalDTO terminal) {
        ResponsDTO responsDTO = Appl.applicationContext.getBean("terminalRepository", TerminalRepository.class).creatTerminal(terminal);
        if (responsDTO.success()) {
            System.out.println(responsDTO.message());
        } else {
            System.out.println(responsDTO.message());
        }
    }

    public void showTerminalList() {
        List<TerminalDTO> terminalList = Appl.applicationContext.getBean("terminalRepository", TerminalRepository.class).getTerminalList();

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
        ResponsDTO responsDTO = Appl.applicationContext.getBean("terminalRepository", TerminalRepository.class).updateTerminal(terminal, address);
        if (responsDTO.success()) {
            System.out.println(responsDTO.message());
        } else {
            System.out.println(responsDTO.message());
        }
    }

    public void changeTerminalStatusByAdmin(String terminalCode, String newTerminalStatus) {
        if (newTerminalStatus.equals("ACTIVE") || newTerminalStatus.equals("NO_ACTIVE") || newTerminalStatus.equals("BLOCKED")) {
            List<TerminalDTO> terminalList = Appl.applicationContext.getBean("terminalRepository", TerminalRepository.class).getTerminalList();
            ResponsDTO result = null;
            if (terminalList == null) {
                System.out.println("Terminal if not exist!!!");
                return;
            } else {
                for (TerminalDTO terminalDTO : terminalList) {
                    if (terminalDTO.getCode().equals(terminalCode)) {
                        result = Appl.applicationContext.getBean("terminalRepository", TerminalRepository.class).changeTerminalStatusByAdmin(terminalCode, newTerminalStatus);
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
        List<TerminalDTO> terminalList = Appl.applicationContext.getBean("terminalRepository", TerminalRepository.class).getTerminalList();
        ResponsDTO result = null;
        if (terminalList == null) {
            System.out.println("Terminal if not exist!!!");
        } else {
            for (TerminalDTO terminalDTO : terminalList) {
                if (terminalDTO.getCode().equals(terminalCode)) {
                    result = Appl.applicationContext.getBean("terminalRepository", TerminalRepository.class).deleteTerminal(terminalCode);
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
        List<TerminalDTO> terminalList = Appl.applicationContext.getBean("terminalRepository", TerminalRepository.class).getTerminalList();
        for (TerminalDTO terminalDTO : terminalList) {
            if (terminalDTO.getCode().equals(terminalCode)&&terminalDTO.getStatus().equals(Status.ACTIVE)){
                return true;
            }
        }
        return false;
    }


}
