package br.com.stockio.use_cases.promoting_employee;

import br.com.stockio.loggers.Logger;
import br.com.stockio.use_cases.promoting_employee.io.inputs.PromotingEmployeeUseCaseInput;
import br.com.stockio.use_cases.specifics.consumers.ConsumerUseCase;

import static br.com.stockio.app_metadata.UseCases.PROMOTING_EMPLOYEE;

public abstract class PromotingEmployeeUseCase extends ConsumerUseCase<PromotingEmployeeUseCaseInput> {

    protected PromotingEmployeeUseCase(Logger logger) {
        super(PROMOTING_EMPLOYEE.getUseCaseMetadata(), logger);
    }
}

