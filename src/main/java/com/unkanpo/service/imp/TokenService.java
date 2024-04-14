package com.unkanpo.service.imp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Service
public class TokenService {

    public String createToken(String userName, String password) {
        String token = "";
        Random random = new Random();
        boolean isUp = random.nextBoolean();
        int jumpCode = random.nextInt(password.length());
        char[] loginInfo = (userName + "_" + password).toCharArray();

        token += jumpCode;
        token +=  isUp ? "1_" : "0_";

        for (int index = 0; index < loginInfo.length; index++) {
            int jumpSpace = Character.codePointAt(loginInfo,index);
            if (jumpSpace == 95) {
                token += "_";
                continue;
            }
            if (isUp) {
                jumpSpace += jumpCode;
            }else {
                jumpSpace -=  jumpCode;
            }
            token += encode(isUp,jumpSpace);
        }
        return token;
    }
    private static int encode(boolean isUp, int jumpSpace) {
        if (!(jumpSpace < 48 || (57 < jumpSpace && jumpSpace < 65) || (90 < jumpSpace && jumpSpace < 97) || jumpSpace > 122)) {
            return jumpSpace;
        }

        int result = jumpSpace;
        if (isUp) {
            if (jumpSpace > 122) {
                result = 48 + (jumpSpace - 122);
            } else if ( 90 < jumpSpace && jumpSpace < 97) {
                result = 97 + (jumpSpace - 90);
            } else if (57 < jumpSpace && jumpSpace < 65) {
                result = 65 + (jumpSpace - 57);
            }
        } else {
            if (jumpSpace < 48) {
                result = 122 - (48 - jumpSpace);
            } else if (jumpSpace > 57 && jumpSpace < 65) {
                result =  57 - (65 - jumpSpace);
            } else if (jumpSpace > 90 && jumpSpace < 97) {
                result = 90 - (97 - jumpSpace);
            }
        }
        return encode(isUp, result);
    }
}
