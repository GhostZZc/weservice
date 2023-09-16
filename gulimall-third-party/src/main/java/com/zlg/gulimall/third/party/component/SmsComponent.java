package com.zlg.gulimall.third.party.component;

import com.zlg.gulimall.third.party.util.HashUtil;
import com.zlg.gulimall.third.party.util.UserRunnable;
import javafx.application.Platform;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SmsComponet
 * @Description:
 * @author: lzg
 * @date: 2023/6/13 16:36
 */
@Component
public class SmsComponent {



    public void sendCode(String phone,String code){
        HashUtil.phone=phone;
        HashUtil.hash=code;
        UserRunnable runnable = new UserRunnable();
        Platform.runLater(runnable);
    }

}
