package br.com.stockio.app_utils.use_case_dependency_wrapper;

import br.com.stockio.app_utils.use_case_dependency_wrapper.exceptions.NullDependencyAdapterException;

import java.util.Optional;

public abstract class UseCaseDependencyWrapper {

    protected <D> D getValueWithNullSafety(D dependency){
        return Optional.ofNullable(dependency).orElseThrow(() -> new NullDependencyAdapterException(this));
    }

}
