# some-core-layer

This is an example of how to use the [clean-arch-enablers](https://github.com/lucioalmeidastockio/clean-arch-enablers) library in your Java app in order to make it follow the Clean Architecture concepts.

For our example, we will consider the use case of promoting an employee, supposing we are developing an employee management system.

The use case workflow is as the represented below:

<br>

![promoting_employee_use_case_workflow](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/workflow.drawio.png)

<br>

In order to implement the use case such as the workflow above, we need some business entities:

<br>

- [Employee](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/entities/Employee.java)
- [Role](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/entities/Role.java)
- [RoleAssignment](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/entities/RoleAssignment.java)

<br>

![entities](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/Entities.png)

<br>

But let's break it down and go easy first...

## First things first
First of all we need to define what kind of UseCase our use case will be. Will it accept inputs? Will it return anything as output?

It could be the case our use case would:

- Have input and output ([_function use case type_](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/main/src/main/java/br/com/stockio/use_cases/specifics/functions/FunctionUseCase.java))
- Have only input ([_consumer use case type_](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/main/src/main/java/br/com/stockio/use_cases/specifics/consumers/ConsumerUseCase.java))
- Have only output ([_supplier use case type_](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/main/src/main/java/br/com/stockio/use_cases/specifics/suppliers/SupplierUseCase.java))
- Have neither input or output ([_runnable use case type_](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/main/src/main/java/br/com/stockio/use_cases/specifics/runnables/RunnableUseCase.java))

So, what will it be? To answer that, let's take a look at some important parts of our workflow in regards to that:

<br>

![i/o](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/io.png)

<br>

Our use case execution starts receiving the ID of the employee being promoted and the ID of the role to which the employee will be assigned to. That by itself indicates our use case accepts input, so it will be either a function or a consumer use case. It will depend on our workflow returning something at the end of the execution.

Turns out, by taking a look at the image above, our workflow won't return anything. The last step before finishing is about incrementing the history of the employee's assignments, not returning anything.

So, our use case is an example of ConsumerUseCase.

<br>

## Let there be PromotingEmployeeUseCase

Since we already know the type of our use case, we can start creating it. It is done by following the steps below:

- Create a [package](https://github.com/lucioalmeidastockio/some-core-layer/tree/1-example-documentation/src/main/java/br/com/stockio/use_cases) with the name "use_cases" at the root of your group ID package: <br>
![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/use_cases_package.png)
 <br>
 
- Inside of it, create [another package](https://github.com/lucioalmeidastockio/some-core-layer/tree/1-example-documentation/src/main/java/br/com/stockio/use_cases/promoting_employee), this time called after the name of your use case: <br>
![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/promoting_employee_use_case_package.png)
 <br>
 
- Inside of the newest package, create a [class to represent the use case contract](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/use_cases/promoting_employee/PromotingEmployeeUseCase.java): <br>
![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/usecase.png)
 <br>
 
- Make the use case class [extend its respective UseCase type](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/use_cases/promoting_employee/PromotingEmployeeUseCase.java#L9) (in this case, ConsumerUseCase): <br>
![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/extending-consumer-use-case.png)
 <br>
 
- Specify the [input type](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/use_cases/promoting_employee/PromotingEmployeeUseCase.java#L4) (since it is a use case which receives input, a type for it must be defined). Create [new packages inside your use case package called "io.inputs"](https://github.com/lucioalmeidastockio/some-core-layer/tree/1-example-documentation/src/main/java/br/com/stockio/use_cases/promoting_employee/io/inputs) and then create the [class for your use case input there](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/use_cases/promoting_employee/io/inputs/PromotingEmployeeUseCaseInput.java) (be sure you make the new class extend the [UseCaseInput](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/main/src/main/java/br/com/stockio/use_cases/io/UseCaseInput.java) type): <br>
![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/usecaseinputtype.png)
<br>

- Implement the [protected constructor of the UseCase class](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/use_cases/promoting_employee/PromotingEmployeeUseCase.java#L11). It will receive an instance that implements the [Logger interface](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/main/src/main/java/br/com/stockio/loggers/Logger.java) (in order to make a logger  instance accessible inside your use case object without making it dirty by knowing which implementation you are using): <br>
![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/constructorloggerparam.png)
<br>

- At the super section, you will pass an instance of [UseCaseMetadata](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/main/src/main/java/br/com/stockio/use_cases/metadata/UseCaseMetadata.java), which is to inform details about your use case for further use at the building phase, along with the Logger instance received through the constructor args: <br>
![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/super.png)
<br>

- Example of creating an instance of UseCaseMetadata below. (I decided to declare the use case as of open access, but you could decide to make it of protected access if you'd want to let the primary adapter layer know that it should implement some security layer for accessing the use case execution. The description I gave, by the way, was very simple... you could make a richer one.) <br>
![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/usecasemetadata.png)
<br>

That's it for the contract of our use case. Take a look at the full picture of our class: <br>
![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/fullpicusecasecontract.png)

## Input details

We've created the type for our input in our use case contract. Now it's time to define what constitutes the input itself. Let's see again what the workflow told us about the input data supposed to be received:

<br>

![](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/images/inputfields.png)

<br>

It says to receive the ID of the employee and the ID of the role. That is what composes our input as a whole. Those are the fields our use case input type will have.

<br>

![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/inputfieldsinclass.png)

<br>

Does the workflow tell us anything more about the input? Well, yes. It says both of the fields are required. They can't be null:

<br>

![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/requiredinputfields.png)

<br>

How to embody that rule to our code?

You could implement a validation yourself for checking whether or not both values are present, but that is not necessary. Your use case instance will automatically run a validation onto your input object before executing its inner logic. If you define some fields as required and they come null it will result in an instance of [InputMappedException](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/main/src/main/java/br/com/stockio/mapped_exceptions/specifics/InputMappedException.java) being thrown along with a message specifying which field triggered such behavior.

Using this approach your input class would look like this:

<br>

![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/notnullinputfield.png)

<br>

That's it. Now those fields are required in order for your use case to execute.

<br>

## Use Case Implementation
