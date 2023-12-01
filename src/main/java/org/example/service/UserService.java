package org.example.service;

import org.example.dto.ProfileDTO;
import org.example.enums.Status;
import org.example.repository.ProfileRepository;
import org.example.utils.ScannerUtils;

import java.util.List;

public class UserService {

    ScannerUtils scanner = new ScannerUtils();
    ProfileRepository profileRepository=new ProfileRepository();



    public ProfileDTO login(ProfileDTO profileDTO) {
        ProfileDTO profile = profileRepository.login(profileDTO);


        return profile;

    }

    public boolean registration(ProfileDTO profile) {

        boolean result = profileRepository.registration(profile);
        return result;
    }


    public void showProfileList() {
        List<ProfileDTO> profiles = profileRepository.getProfileList();
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
