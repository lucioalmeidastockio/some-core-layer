package br.com.stockio.use_cases.promoting_employee.io.inputs;

import br.com.stockio.use_cases.io.UseCaseInput;
import br.com.stockio.use_cases.io.annotations.NotNullInputField;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class PromotingEmployeeUseCaseInput extends UseCaseInput {

    @NotNullInputField
    private final UUID employeeId;

    @NotNullInputField
    private final UUID roleId;

}
