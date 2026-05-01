package com.cargasafe.alert.interfaces.rest.transformers;

import com.cargasafe.alert.domain.model.commands.AcknowledgeAlertCommand;

public class AcknowledgeAlertCommandFromResourceAssembler {

    public static AcknowledgeAlertCommand toCommandFromResource(Long alertId) {
        return new AcknowledgeAlertCommand(alertId);
    }
}
