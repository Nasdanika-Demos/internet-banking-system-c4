```drawio
${representations/drawio/diagram}
```

The the above diagram is a [Code diagram](https://c4model.com/#CodeDiagram), which is used to show how a component is implemented as code.
Diagram elements were taken from the Drawio UML shapes palette.
Icons were added by manually adding ``image=https://cdn.jsdelivr.net/gh/Nasdanika-Models/ecore@master/graph/web-resources/icons/EClass.gif;`` to the style.

## Mapping

The package container maps to the same semantic element as on the API Application Component diagram because:

* The "Mainframe Banking System Facade" element on the API Application Component diagram links to this diagram page.
* The package element on this diagram has ``page-element`` property set to ``true``.   

Semantic elements of the child elements of the "API Application" element are mapped to the ``elements`` reference with ordering by ``label`` comparator:

```yaml
container:
  self:
    elements:
      path: 1
      comparator: label
```          

All code elements within the package are mapped to [c4.Code](https://architecture.models.nasdanika.org/references/eSubpackages/c4/references/eClassifiers/Code/index.html).

## Representation element filtering

Fill color of ``MainframeBankingSystemFacadeImpl`` is set during the generation with the following [code](https://github.com/Nasdanika-Models/architecture/blob/main/demos/internet-banking-system-c4/src/test/java/org/nasdanika/models/architecture/demos/ibs/c4/tests/TestInternetBankingSystemC4SiteGen.java#L44):

```java
@Override
protected void filterRepresentationElement(
		Element representationElement, 
		EObject semanticElement,
		Map<EObject, EObject> registry, 
		ProgressMonitor progressMonitor) {

	super.filterRepresentationElement(representationElement, semanticElement, registry, progressMonitor);
	
	if (representationElement instanceof Node) {
		Node node = (Node) representationElement;
		if ("MainframeBankingSystemFacadeImpl".equals(node.getId())) {
			node.getStyle().put("fillColor", "#ffe6cc");
			node.getStyle().put("strokeColor", "#d79b00");
		}
	}					
}
```

Representation filtering may be used to inject information which is not available during diagram creation or dynamic, but is available during generation.
In this example the background color may represent code component "health" - code coverage, number and severity of SonarQube findings, etc.
At higher level diagrams it may represent, say:

* During construction: development progress - pending, in progress, blocked, behind schedule, ...
* After deployment: container runtime status - errors, CPU load, memory consumption, ... 
