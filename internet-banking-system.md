
```drawio
${representations/drawio/diagram}
```

The the above diagram is a [Container diagram](https://c4model.com/#ContainerDiagram), which is "zoom-in to the system boundary".

## Mapping

### Surroundings -> selectors

"Personal Banking Customer", "E-mail System", and "Mainframe Banking System" diagram elements are mapped to the same semantic elements as on the System Context Diagram using ``selector``s. 
The system context diagram defines the surrounding nodes and this diagram references them.

This is a selector of the "E-mail System":

```yaml
getDocument().getModelElementByProperty('semantic-id', 'microsoft-exchange')
```

The loading logic "carries over" tooltips from the System Context Diagram to this diagram.

### Internet Banking System

The "Internet Banking System" container maps to the same semantic element as on the System Context diagram because:

* The "Internet Banking System" element on the System Context Diagram links to this diagram page.
* The "Internet Banking System" element on this diagram has ``page-element`` property set to ``true``.   

Semantic elements of the child elements of the "Internet Banking System" element are mapped to the ``elements`` reference and ordered with the ``flow`` comparator:

```yaml
container:
  self: 
    elements:
      path: 1
      comparator: 
        flow: 
          fallback: label
          condition: id != 'send-email'
```          

The condition prevents traversal of the "API Application" -> "E-mail System" connection. 
It is not really necessary here - the order would not change without it.
It is provided as an example because connection conditions may be necessary in some cases for proper ordering in diagrams with connection cycles.

### Container elements

All diagram elements within the system container are mapped to [c4.Container](https://architecture.models.nasdanika.org/references/eSubpackages/c4/references/eClassifiers/Container/index.html)s.

The API Application is linked to the "Container Diagram" page. As such, its semantic element is mapped to the "API Application Component Diagram" page element as well allowing further mapping on [that page](references/elements/api-application/index.html).

The API Application diagram element defines ``base-uri`` property as ``%id%/``.
Because "Placeholders" is checked, ``%id%/`` expands to ``api-application/`` during loading. 
``doc-ref`` is set to ``readme.md``, which in combination with ``base-uri`` resolves to [``api-appliction/readme.md``](https://github.com/Nasdanika-Models/architecture/blob/main/demos/internet-banking-system-c4/api-application/readme.md).
