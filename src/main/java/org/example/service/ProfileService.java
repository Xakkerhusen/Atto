package org.example.service;

import lombok.Setter;
import org.example.controller.Appl;
import org.example.dto.ProfileDTO;
import org.example.enums.Status;
import org.example.repository.ProfileRepository;
import org.example.repository.TransactionRepository;
import org.example.utils.ScannerUtils;

import java.util.List;
@Setter
public class ProfileService {
//    ProfileRepository profileRepository=new ProfileRepository();

    ProfileRepository profileRepository;

//ProfileRepository profileRepository;//spring da tajriba oxshamadi

    public ProfileDTO login(ProfileDTO profileDTO) {
        ProfileDTO profile = Appl.applicationContext.getBean("profileRepository", ProfileRepository.class).login(profileDTO);
        return profile;
    }

    public boolean registration(ProfileDTO profile) {

        boolean result = Appl.applicationContext.getBean("profileRepository", ProfileRepository.class).registration(profile);
        return result;
    }


    public void showProfileList() {
        List<ProfileDTO> profiles = Appl.applicationContext.getBean("profileRepository", ProfileRepository.class).getProfileList();
        if (profiles != null) {
            for (ProfileDTO profile : profiles) {
                if (profile.getStatus().equals(Status.ACTIVE)) {
                    System.out.println(profile);
                }else if (profile.getStatus().equals(Status.NO_ACTIVE)) {
                    System.out.println(profile);
                }else if (profile.getStatus().equals(Status.BLOCKED)) {
                    System.out.println(profile);
                }
            }
        }else {
            System.out.println("We have not any profiles");
        }
    }


}
