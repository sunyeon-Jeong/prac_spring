package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.SignupRequestDto;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.entity.UserRoleEnum;
import com.sparta.myselectshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService {
    // Repository(DB)단 객체로 연결
    private final UserRepository userRepository;

    // ADMIN_TOKEN
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    // 회원가입
    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        // RequestDto에서 username, password 가져옴

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        } // Repository에 findByUsername 관련 설정해주기

        String email = signupRequestDto.getEmail();

        UserRoleEnum role = UserRoleEnum.USER; // UserRoleEnum 클래스 객체 role <- 사용자권한
        if (signupRequestDto.isAdmin()) { // Admin이 true 맞다면
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                // 예외 : AdminToken값 맞는지 확인
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN; // UserRoleEnum 클래스 객체 role <- 관리자권한으로 바꿈
        }

        User user = new User(username, password, email, role); // UserEntity 객체 -> 회원가입관련 데이터 저장
        userRepository.save(user); // 회원가입 내용 DB에 저장
    }
}