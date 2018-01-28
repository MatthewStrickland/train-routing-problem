# train-routing-problem
Problem resolving Kiwiland routing information

## How to run
Simply 
`gradlew --console=plain -q run`
or
`./gradlew --console=plain -q run`

### Design
Main class injects Services into an Orchestrator, which in turn is injected into the InputReader.
User inputs the information through the command line, this information gets parsed into ProblemTypes.
The Problem type helps the orchestor determine which service to use, and it is called. 
It is visited by a wrapper of the input from the user, say, A-B, and if it is accepted by the service (passes validation) it will promote the input to the problem solving step.
The solver is only responsible for running the algorithm to get to the correct printed solution.
