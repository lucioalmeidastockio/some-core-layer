package br.com.stockio.app_utils.use_case_dependency_wrapper.exceptions;

import br.com.stockio.app_utils.use_case_dependency_wrapper.UseCaseDependencyWrapper;
import br.com.stockio.mapped_exceptions.specifics.InternalMappedException;

public class NullDependencyAdapterException extends InternalMappedException {
    public NullDependencyAdapterException(UseCaseDependencyWrapper useCaseDependencyWrapper) {
        super("Use case must not receive one of its dependency adapters as a null reference", useCaseDependencyWrapper.getClass().getSimpleName() + " had one of its attributes null");
    }
}
