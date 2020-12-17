package main.service;

import main.api.response.ResultResponse;
import main.api.response.view.UserForResponse;
import main.repository.PostRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    public ResultResponse getAuth(String email) {
        ResultResponse loginResponse = new ResultResponse();
        try {
            main.model.User userTek = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user " + email + " not found"));
            loginResponse.setResult(true);
            UserForResponse userForResultLoginResponse = new UserForResponse();
            userForResultLoginResponse.setId(userTek.getId());
            userForResultLoginResponse.setName(userTek.getName());
            userForResultLoginResponse.setPhoto(userTek.getPhoto());
            userForResultLoginResponse.setEmail(userTek.getEmail());
            userForResultLoginResponse.setModeration(userTek.getIsModerator() == 1);
            userForResultLoginResponse.setModerationCount(userTek.getIsModerator() == 1 ? postRepository.getCountModerationNew("new") : 0);
            userForResultLoginResponse.setSettings(true); //надо добавить

            loginResponse.setUser(userForResultLoginResponse);
            return loginResponse;
        } catch (UsernameNotFoundException e) {
            return loginResponse;
        }

    }


}
