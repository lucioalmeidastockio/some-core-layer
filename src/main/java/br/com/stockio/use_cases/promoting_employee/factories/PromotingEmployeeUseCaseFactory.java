package br.com.stockio.use_cases.promoting_employee.factories;

import br.com.stockio.use_cases.promoting_employee.PromotingEmployeeUseCase;
import br.com.stockio.use_cases.promoting_employee.factories.dependency_wrapper.PromotingEmployeeUseCaseDependencyWrapper;
import br.com.stockio.use_cases.promoting_employee.implementations.PromotingEmployeeUseCaseImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PromotingEmployeeUseCaseFactory {

    private static PromotingEmployeeUseCase useCaseInstance = null;

    public static PromotingEmployeeUseCase getSingletonUseCaseInstanceWith(PromotingEmployeeUseCaseDependencyWrapper useCaseDependencyWrapper){
        if (Optional.ofNullable(useCaseInstance).isPresent())
            return useCaseInstance;
        useCaseInstance = makeNewInstanceWith(useCaseDependencyWrapper);
        return useCaseInstance;
    }

    private static PromotingEmployeeUseCase makeNewInstanceWith(PromotingEmployeeUseCaseDependencyWrapper useCaseDependencyWrapper) {
        return new PromotingEmployeeUseCaseImplementation(
                useCaseDependencyWrapper.getLogger(),
                useCaseDependencyWrapper.getEmployeeRetrievementByIdPortAdapter(),
                useCaseDependencyWrapper.getRoleRetrievementByIdPortAdapter(),
                useCaseDependencyWrapper.getCareerPathRetrievementPortAdapter(),
                useCaseDependencyWrapper.getEmployeeUpdatePortAdapter()
        );
    }

}
