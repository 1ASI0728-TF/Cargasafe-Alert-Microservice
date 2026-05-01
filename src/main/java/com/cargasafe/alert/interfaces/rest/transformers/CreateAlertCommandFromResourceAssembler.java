package com.cargasafe.alert.interfaces.rest.transformers;

import com.cargasafe.alert.domain.model.commands.CreateAlertCommand;
import com.cargasafe.alert.interfaces.rest.resources.CreateAlertResource;

public class CreateAlertCommandFromResourceAssembler {

    public static CreateAlertCommand toCommandFromResource(CreateAlertResource resource) {
        return new CreateAlertCommand(
                resource.deliveryOrderId(),
                resource.alertType(),
                resource.description(),
                resource.notificationChannel(),
                resource.message()
        );
    }
}
