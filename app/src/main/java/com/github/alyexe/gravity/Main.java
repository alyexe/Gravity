package com.github.alyexe.gravity;

import com.github.alyexe.gravity.scenes.MainMenuScene;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.SceneFW;

public class Main extends CoreFW {

    public SceneFW getStartScene() {
        return new MainMenuScene(this);
    }
}
