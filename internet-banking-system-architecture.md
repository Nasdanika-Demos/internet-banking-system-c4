This site was generated as explained below:

* A [Drawio](https://www.drawio.com/) diagram of an Internet Banking System was created based on images and descriptions from [The C4 model for visualising software architecture](https://c4model.com/). Descriptions of elements and diagrams were copied verbatim under the terms of [Creative Commons Attribution 4.0 International License](https://creativecommons.org/licenses/by/4.0/). The diagram uses free icons from [Icons8](https://icons8.com/) to provide visual distinction between architecture elements. 
* The diagram was mapped to the [Architecture model](https://architecture.models.nasdanika.org/) using properties of the diagram elements as explained in the [Beyond Diagrams](https://leanpub.com/beyond-diagrams) book.
* Then the architecture model was transformed to the [HTML Application model](https://html-app.models.nasdanika.org/index.html) and a static web site was generated from that model. For the majority of elements icons were generated from the diagram images of the respective elements.
* The web site is deployed by [GitHub Pages](https://pages.github.com/).

Notes:

* Diagram elements have tooltips with descriptions from the C4 model site.
* A click on a diagram element navigates to the element page.
* The left navigation provides a filter to quickly find a model element by name.
* [Search](search.html) provides full-text search which also searches for text in diagrams. 
* [Glossary](glossary.html) provides a list of "terms" - a dictionary of the language of the system. It needs improvements - currently it shows all actions. Uncheck "Hide UUID" to see the elements. Elements can be filtered using the filter text field.
* There is a link to the [source code on GitHub](https://github.com/Nasdanika-Models/architecture/tree/main/demos/internet-banking-system) in the footer.
* Only [Send e-mail using](references/elements/internet-banking-system/references/outgoingConnections/send-email-using/index.html) relationship is mapped and documented in this demo.

```drawio
${representations/drawio/diagram}
```

The the above diagram is a [System Context diagram](https://c4model.com/#SystemContextDiagram), which is

> a good starting point for diagramming and 
> documenting a software system, allowing you to step back 
> and see the big picture. Draw a diagram showing your system as a box
>  in the centre, surrounded by its users and the other systems that it
> interacts with.

## Mapping

[TOC levels=3-6]

### Root -> c4.Context

The [root](https://drawio.models.nasdanika.org/references/eClassifiers/Root/index.html) of the diagram is mapped to [Context](https://architecture.models.nasdanika.org/references/eSubpackages/c4/references/eClassifiers/Context/index.html)
with the ``type`` property set to ``c4.Context``

```png-resource
../../root-properties.png
```

This documentation was generated from [internet-banking-system-architecture.md](https://github.com/Nasdanika-Models/architecture/blob/main/demos/internet-banking-system-c4/internet-banking-system-architecture.md) markdown file with the image above embedded using ``png-resource`` fenced block
and the diagram embedded using ``drawio`` fenced block with ``representations/drawio/diagram`` expansion token.

Diagram semantic elements were mapped to the root semantic element (``c4.Context``) using the following feature map:

```yaml
container:
  self: 
    elements:
      path: 2
      comparator: label
```

The above mapping means that use the semantic element of this diagram element (root) and add semantic elements of its descendants at path length ``2`` to the ``elements`` reference ordering by ``label``.
Path length is set to 2 because the diagram elements are contained by the background [layer](https://drawio.models.nasdanika.org/references/eClassifiers/Layer/index.html), which is in turn contained by the root.

``page-element`` set to ``true`` specifies that the root semantic element shall also be the [page](https://drawio.models.nasdanika.org/references/eClassifiers/Page/index.html)'s semantic elements. 
Because this is the top-level page (not linked from diagram elements), it also becomes the [document](https://drawio.models.nasdanika.org/references/eClassifiers/Document/index.html)'s semantic element and as such the contents element of the diagram's Ecore resource.

``spec`` sets archtiecture description ``name`` to "Internet Banking System Architecture" - this is what is displayed in the grey navigation bar above:

```yaml
name: Internet Banking System Architecture
```

### Surroundings -> Person, System

"Personal Banking Customer" is mapped to [Person](https://architecture.models.nasdanika.org/references/eSubpackages/c4/references/eClassifiers/Person/index.html).
"E-mail System", and "Mainframe Banking System" diagram elements are mapped to [System](https://architecture.models.nasdanika.org/references/eSubpackages/c4/references/eClassifiers/System/index.html).
They have ``semantic-id`` property to demonstrate its usage. 
Another way to provide meaningful ID's and URL's is to edit element ID's - this approach is used on lower-level diagrams.

The "Personal Banking Customer" element does not have an image associated with it and therefore uses an explicit icon ``spec``:

```yaml
icon: https://img.icons8.com/officel/16/user.png
```

Tooltips are copied from descriptions on the C4 Model diagram.

### Internet Banking System -> System

The "Internet Banking System" diagram element is mapped to [System](https://architecture.models.nasdanika.org/references/eSubpackages/c4/references/eClassifiers/System/index.html).
It is linked to the "Container Diagram" page. As such, its semantic element is mapped to the "Container Diagram" page element as well allowing further mapping on [that page](references/elements/internet-banking-system/index.html).

## Generation

This site was generated with a [JUnit test](https://github.com/Nasdanika-Models/architecture/blob/main/demos/c4/src/test/java/org/nasdanika/models/architecture/demos/c4/tests/TestC4SiteGen.java).
Another way to generate documentation site is to use [Nasdanika CLI](https://docs.nasdanika.org/nsd-cli/index.html) [app/model/site](https://docs.nasdanika.org/nsd-cli/nsd/app/model/site/index.html) command
or [app/model/action](https://docs.nasdanika.org/nsd-cli/nsd/app/model/action/index.html) command and then [app/site](https://docs.nasdanika.org/nsd-cli/nsd/app/site/index.html) command.


