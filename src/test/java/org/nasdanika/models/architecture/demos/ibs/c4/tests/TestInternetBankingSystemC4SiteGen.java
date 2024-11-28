package org.nasdanika.models.architecture.demos.ibs.c4.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.Test;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.capability.emf.ResourceSetRequirement;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.emf.DrawioContentProvider;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.models.app.gen.AppSiteGenerator;
import org.nasdanika.models.ecore.graph.processors.EcoreHtmlAppGenerator;

public class TestInternetBankingSystemC4SiteGen {

	@Test
	public void testCerulean() throws Exception {
		generateInternetBankingSystemSiteWithMappingEcoreActionGenerator(Theme.Cerulean);
	}

	@Test
	public void testSketchy() throws Exception {
		generateInternetBankingSystemSiteWithMappingEcoreActionGenerator(Theme.Sketchy);
	}
	
	@Test
	public void testContentProvider() throws Exception {
		Document document = Document.load(new File("internet-banking-system.drawio"));
		DrawioContentProvider contentProvider = new DrawioContentProvider(document);
		dump(document, null, contentProvider, 0);		
	}
	
	private void dump(Element element, Element parent, DrawioContentProvider contentProvider, int indent) {
		for (int i = 0; i < indent; ++i) {
			System.out.print("  ");			
		}
		String id = element instanceof ModelElement ? " " + ((ModelElement) element).getId() : "";
		System.out.println(contentProvider.getName(element) + " [" + element.getClass().getName()  + id + "] => " + contentProvider.getBaseURI(element).trimFragment());
		assertEquals(parent, contentProvider.getParent(element));
		for (Element child: contentProvider.getChildren(element)) {
			dump(child, element, contentProvider, indent + 1);
		}
	}
		
	private void generateInternetBankingSystemSiteWithMappingEcoreActionGenerator(Theme theme) throws Exception {
		// TODO - representation filtering from capabilities
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Requirement<ResourceSetRequirement, ResourceSet> requirement = ServiceCapabilityFactory.createRequirement(ResourceSet.class);		
		ResourceSet resourceSet = capabilityLoader.loadOne(requirement, progressMonitor);
				
		File ibsDiagramFile = new File("internet-banking-system.drawio").getCanonicalFile();
		Resource ibsResource = resourceSet.getResource(URI.createFileURI(ibsDiagramFile.getAbsolutePath()), true);
		System.out.println(ibsResource.getContents());
		
		Resource ibsResourceDump = resourceSet.createResource(URI.createFileURI(new File("target/ibs.xml").getAbsolutePath()));
		ibsResourceDump.getContents().addAll(EcoreUtil.copyAll(ibsResource.getContents()));
		ibsResourceDump.save(null);
		assertEquals(1, ibsResource.getContents().size());
		
		// Generating an action model
		MutableContext context = Context.EMPTY_CONTEXT.fork();
		
		Consumer<Diagnostic> diagnosticConsumer = d -> d.dump(System.out, 0);		
		
		File actionModelsDir = new File("target\\action-models\\");
		actionModelsDir.mkdirs();
								
		File output = new File(actionModelsDir, "ibs.xmi");
		
		EcoreHtmlAppGenerator htmlAppGenerator = EcoreHtmlAppGenerator.loadEcoreHtmlAppGenerator(
				ibsResource.getContents(), 
				context,
				null, // java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider,			
				null, // Predicate<Object> factoryPredicate,
				null, // Predicate<EPackage> ePackagePredicate,
				diagnosticConsumer,
				progressMonitor);
		
		htmlAppGenerator.generateHtmlAppModel(
				diagnosticConsumer, 
				output,
				progressMonitor);
				
		// Generating a web site
		String rootActionResource = "actions.yml";
		URI rootActionURI = URI.createFileURI(new File(rootActionResource).getAbsolutePath());//.appendFragment("/");
		
		String pageTemplateResource = "page-template-" + theme.name().toLowerCase() + ".yml";
		URI pageTemplateURI = URI.createFileURI(new File(pageTemplateResource).getAbsolutePath());//.appendFragment("/");
		
		String siteMapDomain = "https://nasdanika-demos.github.io/internet-banking-system-c4/" + theme.name().toLowerCase() + "/";		
		AppSiteGenerator actionSiteGenerator = new AppSiteGenerator() {
			
			protected boolean isDeleteOutputPath(String path) {
				return !"CNAME".equals(path);				
			};
			
		};		
		
		Map<String, Collection<String>> errors = actionSiteGenerator.generate(
				rootActionURI, 
				pageTemplateURI, 
				siteMapDomain, 
				new File("docs/"  + theme.name().toLowerCase()), // Publishing to the repository's docs directory for GitHub pages 
				new File("target/ibs-doc-site-work-dir"), 
				true);
		
		int errorCount = 0;
		for (Entry<String, Collection<String>> ee: errors.entrySet()) {
			System.err.println(ee.getKey());
			for (String error: ee.getValue()) {
				System.err.println("\t" + error);
				++errorCount;
			}
		}
		
		System.out.println("There are " + errorCount + " site errors");
		
		if (errorCount != 20) {
			throw new ExecutionException("There are problems with pages: " + errorCount);
		}		
		
	}	
	
}
