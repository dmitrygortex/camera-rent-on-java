package org.example.camerarentweb.controllers;

import org.example.camerarentcontracts.controllers.base.BaseController;
import org.example.camerarentcontracts.viewmodel.collective.BaseViewModel;

public class BaseControllerImpl implements BaseController {
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
