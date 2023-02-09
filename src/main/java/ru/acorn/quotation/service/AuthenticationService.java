package ru.acorn.quotation.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.acorn.quotation.dto.auth.AuthenticationRequest;
import ru.acorn.quotation.dto.auth.AuthenticationResponse;
import ru.acorn.quotation.dto.auth.RegisterRequest;

@Service
@RequiredArgsConstructor
@NoArgsConstructor
public class AuthenticationService {


    public AuthenticationResponse register(RegisterRequest request) {
        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
