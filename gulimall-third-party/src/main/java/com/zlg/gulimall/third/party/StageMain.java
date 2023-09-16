package com.zlg.gulimall.third.party;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * @ClassName: StageMain
 * @Description: javafx启动类
 * @author: lzg
 * @date: 2022/11/22 15:15
 */
public class StageMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Platform.setImplicitExit(false);
        stage.centerOnScreen();
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.setOnCloseRequest(windowEvent -> {
            stage.hide();
        });
    }
}
