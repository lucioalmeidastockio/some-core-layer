## Summary 📑
- [What is this?](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/README.md#what-is-this)
- [First things first](https://github.com/lucioalmeidastockio/some-core-layer/tree/1-example-documentation#first-things-first)
- [Let there be PromotingEmployeeUseCase](https://github.com/lucioalmeidastockio/some-core-layer/tree/1-example-documentation#let-there-be-promotingemployeeusecase)
- [Input details](https://github.com/lucioalmeidastockio/some-core-layer/tree/1-example-documentation#input-details)
- [Use Case Implementation](https://github.com/lucioalmeidastockio/some-core-layer/tree/1-example-documentation#use-case-implementation)
   - [Abstraction, baby! Let's use Ports!](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/README.md#abstraction-baby-lets-use-ports)
   - [Time for Business Entities!](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/README.md#time-for-business-entities)
   - [First half of our use case implementation](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/README.md#first-half-of-our-use-case-implementation)
   - [Second half of our use case implementation](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/README.md#second-half-of-our-use-case-implementation)
- Use case factory
   - Factory package
   - Factory class 

<br>

## What is this? 🤔

This is a step-by-step breakdown example of how to use the [clean-arch-enablers](https://github.com/lucioalmeidastockio/clean-arch-enablers) library in your Java app. Understanding and applying the practices described in this text will enable your code to meet the Clean Architecture principles at ease.

For our example, we will consider the use case of promoting an employee, supposing we are developing an employee management system.

The use case workflow is as the represented below:

<br>

![promoting_employee_use_case_workflow](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/workflow.drawio.png)

<br>

Let's break it down!

<br>

## First things first☝️
First of all we need to define what kind of UseCase it will be. Will it accept inputs? Will it return anything as output?

It could be the case it would:

- Have input and output ([_function use case type_](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/main/src/main/java/br/com/stockio/use_cases/specifics/functions/FunctionUseCase.java))
- Have only input ([_consumer use case type_](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/main/src/main/java/br/com/stockio/use_cases/specifics/consumers/ConsumerUseCase.java))
- Have only output ([_supplier use case type_](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/main/src/main/java/br/com/stockio/use_cases/specifics/suppliers/SupplierUseCase.java))
- Have neither input or output ([_runnable use case type_](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/main/src/main/java/br/com/stockio/use_cases/specifics/runnables/RunnableUseCase.java))

So, what will it be? To answer that, let's take a look at some important parts of our workflow in regards to that:

<br>

![i/o](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/io.png)

<br>

Our use case execution starts receiving the ID of the employee being promoted and the ID of the role to which the employee will be assigned to. That by itself indicates our use case accepts input, so it will be either a function or a consumer use case. The final conclusion will depend on our workflow returning something at the end of the execution.

Turns out, by taking a look at the image above, our workflow won't return anything. The last step before finishing is about incrementing the history of the employee's assignments, not returning anything.

So, our use case is an example of ConsumerUseCase.

| Use Case Type | (I)nput | (O)utput |
| :---: | :---: | :---: |
| FunctionUseCase | ✔️ | ✔️ |
| ConsumerUseCase | ✔️ | ✖️ |
| SupplierUseCase | ✖️ | ✔️ |
| RunnableUseCase | ✖️ | ✖️ |

<br>

## Let there be PromotingEmployeeUseCase 💥

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
  - Here's an example of how to create an instance of UseCaseMetadata. (I decided to declare the use case as one of _open access_, but you could decide to make it of _protected access_ if you'd want to let the primary adapter layer know that it should implement some security layer for accessing the use case execution. The description I gave, by the way, was very simple... you could make a richer one.) <br>
![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/usecasemetadata.png)
<br>

That's it for the contract of our use case. Take a look at the full picture of our class: <br>
![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/fullpicusecasecontract.png)

<br>

## Input details ⤵️

We've created the type for our input in our use case contract. Now it's time to define what constitutes the input itself. Let's see again what the workflow told us about the input data supposed to be received:

<br>

![](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/images/inputfields.png)

<br>

It says to receive the ID of the employee and the ID of the role. That is what composes our input as a whole. So, those are the fields our use case input type will have.

<br>

![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/inputfieldsinclass.png)

<br>

Does the workflow tell us anything more about the input? Well, yes. It says both of the fields are required. They can't be null:

<br>

![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/requiredinputfields.png)

<br>

How to embody that rule to our code?

You could implement a validation yourself for checking whether or not both values are present, but that is not necessary. Your use case instance will automatically run a validation onto your input object before executing its inner logic. If you define some fields as required and they come null into your use case object it will result in an instance of [InputMappedException](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/main/src/main/java/br/com/stockio/mapped_exceptions/specifics/InputMappedException.java) being thrown along with a message specifying what triggered such behavior.

Using this approach your input class would look like this:

<br>

![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/notnullinputfield.png)

<br>

That's it. Now those fields are required in order for your use case to execute.

<br>

## Use Case Implementation ▶️

Getting past the point of defining the use case type and establishing the input model, it's time to go into the specifics of our use case execution rules.

Let's take another look at our workflow:

<br>

![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/ofportsandentities.png)

<br>

Some parts of our workflow require us to make contact with components from the _external world_, such as databases or other services. That means some kind of specific technology, library or framework will be used in order to accomplish that. The problem with that is the fact we can't make our use case layer coupled to the set of technologies we chose to use at a certain moment. That is because if tomorrow, next week, next month or next year we decide to change our decision we must be able to get it done without having to change anything in our use case logical core layer. 

How is it possible?

### Abstraction, baby! Let's use Ports! 🚪

<br>

Each time we need to make contact with some external component, we will just create a Port for that. Ports will be a layer of abstraction in which we will just trust whoever implements them will do it rightly. If our objective is to retrieve an employee by its ID, we'll create a Port for that. If the actual retrievement will be done by fetching an API or querying a table in a database directly, that's none of the use case logical core layer's business. We'll just rely on Ports.

So to validate if the employee really exists, what we are going to do is to create a Port for retrieving an optional of employee by its ID. The same will be done for the role's preexistence validation.

But let's get lowprofile about it right now. What about the other part of the flow? The part which was described as being possible to be done internally once the data would've been already retrieved. That would be: finishing the current role assignment of an employee, assigning a new role and incrementing the employee's history of assingments. Of course at some point we'll need to persist those changes, but to make those changes in the first place we don't need to contact any external component once the data is present.

Smells like entity spirit.

It is pure logic. Pure business rule. It is the perfect fit for business entities. By the way, we didn't even mention them yet, but they are right there, in front of us, screaming to be noticed. So let's take a look at how they could take form in our code.

### Time for Business Entities! 🕴️

<br>

![entities](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/Entities.png)

One way of representing entities in code would be to create a package called "entities" at the same level as "use_cases". At its root level the modeling and abstract contracts of each entity would be created as well as a subpackage for their implementations. Once done that, another subpackage called "factories" would be created at the same level as the "implementations" subpackage, and there the factories to each entity would be created, then becoming the official manner of instantiating entity objects without having to directly refer to their concrete implementations.

![entity_package](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/entitiespackage.png)

That being stated, let's jump into the entities themselves as code.

### 1. [Employee](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/entities/Employee.java)
The Employee wil be an entity with some behaviors: assuming a new role, which implies in ending a current assignment if any is present, which makes it required to exist a method for retrieving the current assignment.

It is inside of the [Employee entity implementation](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/entities/implementations/EmployeeImplementation.java) that this logic will be at!

Take a look:

- [Assuming new role](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/entities/implementations/EmployeeImplementation.java#L18) <br>
![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/assumingnewrole.png)
<br>

- [Ending current assignment](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/entities/implementations/EmployeeImplementation.java#L34) <br>
![](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/images/endingcurrentassingmentifany.png)
<br>

- [Getting current role](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/entities/implementations/EmployeeImplementation.java#L27) <br>
![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/gettingcurrentroleifany.png)
<br>

### 2. [RoleAssignment](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/entities/RoleAssignment.java)
The RoleAssignment represents the relation between an employee and a role, having the time the relation started and, if being the case, the time when the relation ended. It is in its [implementation](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/entities/implementations/RoleAssignmentImplementation.java) that the field of 'endingMoment' is filled with the moment of the assignment closure when the method is called.

See it for yourself:

- [Filing the ending moment field with the current date](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/entities/implementations/RoleAssignmentImplementation.java#L10) <br>
![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/endassignmentmethod.png)

### 3. [Role](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/entities/Role.java)
The Role entity is just a model with some attributes. No behavior is within it.

----

Each entity has an abstract representation and its respective concrete actual implementation. Take a look at each of them below:

- #### Employee
  - Abstract <br> ![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/employeeentityabstraction.png) <br>


  - Implementation <br> ![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/employeeentityimplementation.png) <br>


- #### RoleAssignment
  - Abstract <br> ![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/roleassignmententityabstraction.png) <br>


  - Implementation <br> ![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/roleassignmententityimplementation.png) <br>


- #### Role
  - Abstract <br> ![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/roleentityabstraction.png) <br>


  - Implementation <br> ![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/roleentityimplementation.png) <br>

Having said and done all of the above, let's see how the Use Case implementation takes place and orchestrates the entities in order to accomplish the final objective.

### First half of our use case implementation 🌗

![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/firsthalfusecaseimplementation-injectingstuff.png) <br> 

If you actually read the code in the image above, you noticed that we have a couple of Port instances being injected in our use case. If you are asking yourself how does a Port look like, remember: a port is just a contract, an abstraction! 

Their contracts follow the same pattern of Use Case types in regards to I/O specifications:

| Port Type | (I)nput | (O)utput |
| :---: | :---: | :---: |
| FunctionPort | ✔️ | ✔️ |
| ConsumerPort | ✔️ | ✖️ |
| SupplierPort | ✖️ | ✔️ |
| RunnablePort | ✖️ | ✖️ |

<br>

- EmployeeRetrievementByIdPort (FunctionPort) <br> ![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/functionport1.png) <br> _Receives the employee ID and returns an optional of employee._ <br>

<br>

- RoleRetrievementByIdPort (FunctionPort) <br> ![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/functionport2.png) <br> _Receives the role ID and returns an optional of role._ <br>

<br>

- CareerPathRetrievementPort (SupplierPort) <br> ![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/supplierport.png) <br> _Returns a map of role ID per roles that are allowed for succession._ <br>

<br>

- EmployeeAssignmentUpdatePort (ConsumerPort) <br> ![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/consumerport.png) <br> _Receives the employee to update it._ <br>

That is the first half of the implementation. Let's dive into the second half of it.

### Second half of our use case implementation 🌓

![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/usecaseinternallogic.png)

1. We try to retrieve the employee. If the port adapter instance returns an empty optional, we assume the employee doesn't exist at the datasource, so we throw EmployeeNotFoundByIdException, which extends NotFoundMappedException (which would map to a 404 status code if the use case would've been dispatched via a REST Endpoint). It is important to whenever throwing custom exceptions making sure they extend some MappedException (InputMappedException, NotFoundMappedException or InternalMappedException), because when your use case instance catches an exception being thrown during its execution, if the exception is a subtype of MappedException it understands it was intended to happen, it assumes it is part of your design, so it will just let it pass through so some custom handler of yours handles it. Otherwise, if the exception is raw, not extending any type of MappedException, the use case instance will consider it as an unexpected exception, so it will catch it and handle it as something going unexpectedly wrong, which would be more like, for example, a unmapped 5xx error than a previously mapped 5xx or 4xx.


2. We try to retrieve the role in the same fashion as the employee retrievement: If no role instance is found, we throw RoleNotFoundByIdException, which extends NotFoundMappedException (which would map to a 404 status code if the use case would've been dispatched via a REST Endpoint).


3. We try to get the current role assignment of the previously fetched employee (if any is retrieved indeed). If there is a current assignment, we ask for the CareerPathRetrievementPort to get the mapping of the available career path, so we can validate if the candidate role to be assigned to the employee is actually allowed. If the candidate role is not among the allowed-next-roles to the current role assigned to the employee, we throw RoleNotAllowedException, which extends InputMappedException (which would map to a 400 status code if the use case would've been dispatched via a REST Endpoint).


4. Up to this point if the employee and the role were actually found and there was no problem with the career path in relation to the selected role for promotion, it is all good for making it official: it is time for promoting this employee to the new role. The Employee entity assumeNewRole method is executed, which under the hood ends the current assignment if any is present and increments the employee's assignment history with a new Role Assignment record.


5. With all being done, it comes to the moment of persisting the changes somewhere, so we call the EmployeeAssignmentUpdatePort execution, finishing the flow of our use case.

That is it for our use case implementation. Now we are good to go preparing it to be used outside at other layer.

## Use case factory 🏭

Now we need to make it available to retrieve an instance of our use case. The way I particularly chose to do it was via a factory class. The factory will instantiate a singleton object for our use case, meaning only one instance of our use case will be made from the moment we call the factory creation method.

In order to the use case to be instantiated we need to pass its dependencies to its constructor, so the factory needs to have them somehow to accomplish the objective of creating a new instance of our use case.

The way I did this was creating a class for wrapping those dependencies and then just passing one object of it to the factory. Let's take a look at it from the beggining:

### Factory package 📦

![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/usecasefactorypackage.png)

As you can see above, I created a package called "[factories](https://github.com/lucioalmeidastockio/some-core-layer/tree/1-example-documentation/src/main/java/br/com/stockio/use_cases/promoting_employee/factories)" in which at its root there is the main class for the factory itself and a subpackage called "[dependency_wrapper](https://github.com/lucioalmeidastockio/some-core-layer/tree/1-example-documentation/src/main/java/br/com/stockio/use_cases/promoting_employee/factories/dependency_wrapper)", where the class for wrapping all the dependencies I mentioned is at.

### Factory class 🏗️

![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/usecasefactory.png)

Pretty straightforward, ain' it? It just instantiates an object that will only be instantiated if its variable wasn't filled yet. To instantiate it we pass the attributes wrapped within the [PromotingEmployeeUseCaseDependencyWrapper](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/use_cases/promoting_employee/factories/dependency_wrapper/PromotingEmployeeUseCaseDependencyWrapper.java) object.

Speaking of which, let's take a look at how I implemented the wrapper:

![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/usecasedependencywrapper.png)

Notice how each getter method calls internally another method, the 'getValueWithNullSafety'. This method is inherited from the [UseCaseDependencyWrapper](https://github.com/lucioalmeidastockio/some-core-layer/blob/1-example-documentation/src/main/java/br/com/stockio/app_utils/use_case_dependency_wrapper/UseCaseDependencyWrapper.java) class that I created specifically for this project, but it can be the case I add it to the library itself so it won't be necessary to create it all over again whenever we want to wrap use case dependencies to pass to a factory and guarantee they are not null as we pass them to the use case constructor. Anyhow, check it out:

![](https://raw.githubusercontent.com/lucioalmeidastockio/some-core-layer/1-example-documentation/images/usecasedependencywrapperbaseclass.png)




