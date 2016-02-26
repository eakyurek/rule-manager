This project is a simple validation rule engine.
With this you can create rules for your service methods, relate them with each other and run before your service methods.

This should not be confused with a complex BRMS.

This project is designed and developed for a necesity in spare time and should not be used before extensive testing.

##Usage
Using is simple.

* First you should create some rule classes by extending Rule class.
* After all you have to do is create a RuleGraph object, populate it with Rule objects, relate them if necessary and validate the graph.

You can create different Validator classes for graph validation simply by implementing GraphValidator.
The default is BasicGraphValidator which traverses the graph from top down. It validates rules only if all of its parent's are valid.

##Example

![Alt text](https://drive.google.com/uc?export=view&id=0B_CirRFOmDCybW1qSm9qcGJwTWs "Optional title")

```java
public class DummyRule extends Rule {
	private String name;
	private boolean valid;

	public DummyRule(String name, boolean valid) {
		this.name = name;
		this.valid = valid;
	}

	@Override
	protected RuleOutput runner() {
		System.out.println(name + ": " + valid);
		return new RuleOutput(valid);
	}
}
```

```java
RuleGraph graph = new RuleGraph(new BasicGraphValidator());
		
Rule A = new DummyRule("A", true);
Rule B = new DummyRule("B", true);
Rule C = new DummyRule("C", true);
Rule D = new DummyRule("D", false);
Rule E = new DummyRule("E", true);
Rule F = new DummyRule("F", true);
Rule G = new DummyRule("G", true);

graph.addRule(A);
graph.addRule(B);
graph.addRule(C);
graph.addRule(D);
graph.addRule(E);
graph.addRule(F);
graph.addRule(G);

graph.addRelation(A, C);
graph.addRelation(A, D);
graph.addRelation(B, D);
graph.addRelation(C, E);
graph.addRelation(C, F);
graph.addRelation(D, F);
graph.addRelation(E, G);
graph.addRelation(F, G);

GraphOutput output = graph.validateGraph();
```

```
A: true
B: true
C: true
D: false
E: true
```
