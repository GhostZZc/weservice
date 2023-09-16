package com.zlg.gulimall.third.party.util;

import javafx.stage.Stage;

/**
 * @ClassName: UserStage
 * @Description: 用户随机码画面
 * @author: lzg
 * @date: 2022/11/22 14:37
 */
public class UserStage extends Stage {
    private static final UserStage userStage = new UserStage();

    private UserStage() {
    }

    public static UserStage getInstance(){
        return userStage;
    }
}
