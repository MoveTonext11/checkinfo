package com.carkeyboard.support;


import com.carkeyboard.view.InputView;
import com.carkeyboard.view.KeyboardView;

/**
 * 建议使用
 * @author 陈哈哈 (yoojiachen@gmail.com)
 */
@Deprecated
public class KeyboardInputController extends com.carkeyboard.KeyboardInputController {

    private KeyboardInputController(KeyboardView keyboardView, InputView inputView) {
        super(keyboardView, inputView);
    }
}
