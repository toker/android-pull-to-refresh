package com.handmark.pulltorefresh.configuration.xml;


import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

import com.handmark.pulltorefresh.configuration.xml.XmlPullParserWrapper.DocumentState;
import com.handmark.pulltorefresh.library.internal.Assert;
/**
 * 
 * @author NBP
 *
 * @param <R>
 */
public abstract class XmlPullNodeParser<R> {
	private final XmlPullParserWrapper parser;
	private final XmlPullNode rootNode;
	
	private boolean isParsed = false;
	
	public XmlPullNodeParser(XmlPullParserWrapper parser) {
		Assert.notNull(parser, "XmlPullParser");
//		Assert.notNull(rootNode, "XmlPullNode");
		this.parser = parser;
//		this.rootNode = rootNode;
		this.rootNode = generateRootNode();
	}

	protected abstract XmlPullNode generateRootNode();
	/**
	 * 
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public final R parse() throws XmlPullParserException, IOException {

		// avoid duplicate parsing 
		if ( isParsed == true ) {
			return getResult();
		}
		
		// First, search the root node.
		DocumentState documentState;
		
		String rootName = rootNode.getName();
		documentState = parser.nextStartTagByName(rootName);
		
		if ( documentState.END.equals(documentState)) {
			throw new XmlPullParserException(rootName + " tag has not found.");
		}
		parseInternal(rootNode);
		isParsed = true;
		return getResult();
	}
	/**
	 * 
	 * @return
	 */
	protected abstract R getResult();

	private void parseInternal(XmlPullNode currentNode) throws XmlPullParserException, IOException {
		// WARNING : too many permissions to node
		currentNode.getCallback().process(parser);
		
		while ( true ) {
	
			// if document is end without end tag of this current node, it throws the exception below. 
			if ( parser.isEndDocument() ) {
				throw new XmlPullParserException("XML document is invalid.");
			}			
			
			// if the end tag has found, parsing this scope is being ended.
			if ( parser.isEndTag() && parser.matchCurrentTagName(currentNode.getName())) {
				break;
			}
			
			// get next tag
			parser.next();
			
			// when a child node has found, deeply parsing
			if ( parser.isStartTag() ) {
				
				String currentTagName = parser.getName();
				XmlPullNode childNode = currentNode.getChild(currentTagName);
				if ( childNode != null ) {
					// recursively!
					parseInternal(childNode);
				}
			}
		

		}
	} 
}
