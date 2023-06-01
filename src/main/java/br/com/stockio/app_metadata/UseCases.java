package br.com.stockio.app_metadata;

import br.com.stockio.use_cases.metadata.UseCaseMetadata;
import br.com.stockio.use_cases.promoting_employee.PromotingEmployeeUseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UseCases {

    PROMOTING_EMPLOYEE(UseCaseMetadata.ofOpenAccessUseCase(PromotingEmployeeUseCase.class, "Use case for promoting an employee"));

    private final UseCaseMetadata useCaseMetadata;

}




