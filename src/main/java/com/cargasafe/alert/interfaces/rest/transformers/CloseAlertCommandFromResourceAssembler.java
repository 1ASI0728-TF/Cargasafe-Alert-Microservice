package com.cargasafe.alert.interfaces.rest.transformers;

import com.cargasafe.alert.domain.model.commands.CloseAlertCommand;

public class CloseAlertCommandFromResourceAssembler {

    public static CloseAlertCommand toCommandFromResource(Long alertId) {
        return new CloseAlertCommand(alertId);
    }
}
