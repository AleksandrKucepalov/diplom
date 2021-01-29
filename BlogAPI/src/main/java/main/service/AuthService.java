package main.service;

import com.github.cage.Cage;
import com.github.cage.GCage;
import main.api.request.MyRequest;
import main.api.request.PasswordRequest;
import main.api.request.RegisterRequest;
import main.api.response.CaptchaResponse;
import main.api.response.ResultResponse;
import main.api.response.view.UserForResponse;
import main.mail.MyConstants;
import main.model.CaptchaCode;
import main.model.User;
import main.repository.CaptchaRepository;
import main.repository.PostRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

import static org.imgscalr.Scalr.resize;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CaptchaRepository captchaRepository;
    @Autowired
    public JavaMailSender emailSender;

    public ResultResponse getAuthResponse(String email) {
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


    public ResultResponse getRestoreResponse(String email) {

        ResultResponse loginResponse = new ResultResponse();
        try {
            main.model.User userTek = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user " + email + " not found"));
            if (userTek != null) {
                String code = getHash(50);
                userTek.setCode(code);
                userRepository.save(userTek);
                //отправка письма
                MimeMessage message = emailSender.createMimeMessage();

                boolean multipart = true;

                MimeMessageHelper helper = null;
                try {
                    helper = new MimeMessageHelper(message, multipart, "utf-8");


                    String htmlMsg = "http://localhost:8081/login/change-password/" + code ;

                    message.setContent(htmlMsg, "text/html");
                    helper.setTo(email);
                    helper.setSubject("API password change");
                    this.emailSender.send(message);
                    loginResponse.setResult(true);

                } catch (MessagingException e) {
                    e.printStackTrace();
                    return loginResponse;
                }
                return loginResponse;
            }
            return loginResponse;
        } catch (UsernameNotFoundException e) {
            return loginResponse;
        }
    }

    public ResultResponse getPasswordResponse(PasswordRequest passwordRequest) {

        ResultResponse loginResponse = new ResultResponse();
        try {
            main.model.User userTek = userRepository.findByCode(passwordRequest.getCode()).orElseThrow(() -> new UsernameNotFoundException("code not found"));
            if (userTek != null) {
                captchaRepository.deleteCaptcha60min(new Date());
                int countCaptcha = captchaRepository.countByCodeAndAndSecretCode(passwordRequest.getCaptcha(), passwordRequest.getCaptchaSecret());
                if (countCaptcha == 1) {
                    String password = passwordRequest.getPassword();
                    if (password.length() < 6) {
                        Map<String, Object> errors = new HashMap<>();
                        errors.put("password", "Пароль короче 6-ти символов");
                        loginResponse.setErrors(errors);
                    } else {
                        userTek.setPassword(password);
                        userRepository.save(userTek);
                        loginResponse.setResult(true);
                    }
                } else {
                    Map<String, Object> errors = new HashMap<>();
                    errors.put("captcha", "Капча введена не верно");
                    loginResponse.setErrors(errors);
                }

                return loginResponse;
            }
            Map<String, Object> errors = new HashMap<>();
            errors.put("code", "Ссылка для восстановления пароля устарела.\n" +
                    " <a href=\n" +
                    " \\\"/auth/restore\\\">Запросить ссылку снова</a>");
            loginResponse.setErrors(errors);
            return loginResponse;
        } catch (UsernameNotFoundException e) {
            return loginResponse;
        }
    }

    public ResultResponse getRegisterResponse(RegisterRequest registerRequest) {

        ResultResponse loginResponse = new ResultResponse();
        try {
            main.model.User userTek = userRepository.findByEmail(registerRequest.getEmail()).orElse(null);
            if (userTek == null) {
                captchaRepository.deleteCaptcha60min(new Date());
                int countCaptcha = captchaRepository.countByCodeAndAndSecretCode(registerRequest.getCaptcha(), registerRequest.getCaptchaSecret());
                if (countCaptcha == 1) {
                    String password = registerRequest.getPassword();
                    if (password.length() < 6) {
                        Map<String, Object> errors = new HashMap<>();
                        errors.put("password", "Пароль короче 6-ти символов");
                        loginResponse.setErrors(errors);
                    } else {
                        User userNew = new User();
                        userNew.setPassword(new BCryptPasswordEncoder().encode(password));
                        userNew.setEmail(registerRequest.getEmail());
                        userNew.setName(registerRequest.getName());
                        userNew.setRegTime(new Date());
                        userRepository.save(userNew);
                        loginResponse.setResult(true);
                    }
                } else {
                    Map<String, Object> errors = new HashMap<>();
                    errors.put("captcha", "Капча введена не верно");
                    loginResponse.setErrors(errors);
                }

                return loginResponse;
            }
            Map<String, Object> errors = new HashMap<>();
            errors.put("email", "Такой email уже зарегестрирован");
            loginResponse.setErrors(errors);
            return loginResponse;
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return loginResponse;
        }
    }

    public ResultResponse getMyResponse(MyRequest myRequest, String email) {

        ResultResponse loginResponse = new ResultResponse();
        try {
            main.model.User userTek = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("code not found"));
            if (userTek != null) {
                main.model.User userEmail = userRepository.findByEmail(myRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("code not found"));
                if (userEmail == null) {
                    //добавить проверку запроса
                    String password = myRequest.getPassword();
                    if (password.length() < 6) {
                        Map<String, Object> errors = new HashMap<>();
                        errors.put("password", "Пароль короче 6-ти символов");
                        loginResponse.setErrors(errors);
                    } else {
                        userTek.setPassword(new BCryptPasswordEncoder().encode(password));
                        userTek.setEmail(myRequest.getEmail());
                        userTek.setName(myRequest.getName());
                        userRepository.save(userTek);
                        loginResponse.setResult(true);
                    }
                } else {
                    Map<String, Object> errors = new HashMap<>();
                    errors.put("email", "Такой email уже зарегестрирован");
                    loginResponse.setErrors(errors);
                }

                return loginResponse;
            }
            return loginResponse;
        } catch (UsernameNotFoundException e) {
            return loginResponse;
        }
    }


    public CaptchaResponse captcha() {

        try {
            Cage cage = new GCage();
            String token = cage.getTokenGenerator().next();
            byte[] fileContent = cage.draw(token);

            File newFile = File.createTempFile(getHash(10), ".jpg");
            FileOutputStream fos = new FileOutputStream(newFile);
            fos.write(fileContent);
            fos.close();

            File file = ChangeSize(newFile, 100, 35);

            byte[] array = Files.readAllBytes(file.toPath());
            String secret = getHash(20);
            String encodedString = Base64
                    .getEncoder()
                    .encodeToString(array);
            CaptchaResponse captchaResponse = new CaptchaResponse();
            captchaResponse.setSecret(secret);
            captchaResponse.setImage("data:image/png;base64, " + encodedString);

            CaptchaCode captchaCode = new CaptchaCode();
            captchaCode.setCode(token);
            captchaCode.setSecretCode(secret);
            captchaCode.setTime(new Date());
            captchaRepository.save(captchaCode);
            captchaRepository.deleteCaptcha60min(new Date());

            return captchaResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return new CaptchaResponse();
        }
    }

    //вспомогательные методы
    public String getHash(int length) {

        String symbols = "abcdefghijklmnopqrstuvwxyz1234567890";
        String random = new Random().ints(length, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
        return random;
    }


    public File ChangeSize(File file, int newWidth, int newHeight) throws Exception {

        BufferedImage image = ImageIO.read(file);
        BufferedImage newImage = resize(image, newWidth, newHeight);
        File newFile = File.createTempFile(getHash(10), ".jpg");
        ImageIO.write(newImage, "jpg", newFile);
        return newFile;
    }
}
