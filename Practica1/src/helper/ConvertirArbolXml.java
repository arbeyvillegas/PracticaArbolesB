/**
 * 
 */
package helper;

import java.io.StringWriter;

import javax.servlet.http.HttpSession;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;

import domain.BPTree;
import domain.BTree;
import domain.BTree.Node;
import domain.BxTree;
import domain.component.BInfo;

/**
 * @author M
 *
 */
public class ConvertirArbolXml {
	public static String leerArbol(HttpSession sesion, String arbol){
		String xml = null;
		String tipoArbol = CacheArboles.getTipoArbol(sesion);
		switch (tipoArbol) {
		case CacheArboles.ArbolB:
			BTree<BInfo> arbolB = CacheArboles.getCache(sesion, arbol);
			xml = ConvertirArbolXml.leerArbolBTree(arbolB);
			break;
		case CacheArboles.ArbolBPlus:
			BPTree<Integer, Long> arbolBPlus = CacheArboles.getCache(sesion,
					arbol);
			xml = ConvertirArbolXml.leerArbolBTree(arbolBPlus);
			break;
		}

		return xml;
	}

	public static <T> String leerArbolBTree(T arbolT) {
		String resultado=null;
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			Element root = document.createElement("raiz");
			document.appendChild(root);

			if(arbolT instanceof BTree) {
				BTree<BInfo> arbol=(BTree)arbolT;
				root.appendChild(goPreOrderBTree(arbol.getRoot(), document));
			}else if(arbolT instanceof BPTree) {
				BPTree<Integer,Long> arbol=(BPTree)arbolT;
				root.appendChild(goPreOrderBPlus(arbol.getRoot(), document));
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource domSource = new DOMSource(document);
			StringWriter stringWriter=new StringWriter();
			StreamResult streamResult = new StreamResult(stringWriter);
			transformer.transform(domSource, streamResult);
			resultado=stringWriter.toString();
		}catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}	

	private static Element goPreOrderBTree(BTree.Node nodo, Document document) {
		Element elemento = null;
		if (nodo != null) {
			elemento = document.createElement("indiceBTree");
			BInfo info = (BInfo) nodo.getInfo();
			elemento.setAttribute("clave", String.valueOf(info.getDato()));
			elemento.setAttribute("direccion", String.valueOf(info.getDireccion()));
			if (nodo.getLeft() != null)
				elemento.appendChild(goPreOrderBTree(nodo.getLeft(), document));
			if (nodo.getRight() != null)
				elemento.appendChild(goPreOrderBTree(nodo.getRight(), document));
		}

		return elemento;
	}
	
	private static Element goPreOrderBPlus(BPTree<Integer, Long>.Node nodo,Document document) {
		Element elemento = null;
		Element grupo=null;
		if(nodo!=null) {
			grupo = document.createElement("nodoBPlusTree");
			if(nodo instanceof BPTree.GuideNode) {
				BPTree<Integer, Long>.GuideNode guideNode=(BPTree.GuideNode)nodo;
				for(int i=0;i< guideNode.keys.size();i++) {
					if(guideNode.keys.get(i)!=null) {
						elemento = document.createElement("indiceBPlusTree");
						elemento.setAttribute("clave", String.valueOf(guideNode.keys.get(i)));
						grupo.appendChild(elemento);
					}
				}
				for(int i=0;i< guideNode.children.size();i++) {
					if(guideNode.children.get(i)!=null) {
//						elemento = document.createElement("indiceBPlusTree");
//						elemento.setAttribute("clave", String.valueOf(guideNode.keys.get(i)));
//						grupo.appendChild(elemento);
						grupo.appendChild(goPreOrderBPlus(guideNode.children.get(i),document));
					}
				}
//				elemento.appendChild(goPreOrderBPlus(guideNode.children.get(i),document));
//				 guideNode.children.get(i)!=null
			}else if(nodo instanceof BPTree.LeafNode) {
				BPTree<Integer, Long>.LeafNode leafNode=(BPTree.LeafNode)nodo;
				for(int i=0;i<leafNode.keys.size();i++) {
					if(leafNode.keys.get(i)!=null && leafNode.values.get(i)!=null) {
						elemento = document.createElement("indiceBPlusTree");
						elemento.setAttribute("clave", String.valueOf(leafNode.keys.get(i)));
						elemento.setAttribute("direccion", String.valueOf(leafNode.values.get(i)));
						grupo.appendChild(elemento);
					}
				}
				
			}
			
		}
		return grupo;
	}
}
