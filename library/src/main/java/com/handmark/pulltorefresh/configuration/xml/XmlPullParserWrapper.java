package com.handmark.pulltorefresh.configuration.xml;


import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.handmark.pulltorefresh.library.internal.Assert;

class XmlPullParserWrapper implements XmlPullParser {

	private final XmlPullParser parser;

	public XmlPullParserWrapper(XmlPullParser parser) {
		Assert.notNull(parser, "XmlPullParser");
		this.parser = parser;
	}

	public static enum DocumentState {
		READ, FOUNDTAG, END;
	}

	public boolean isStartTag() throws XmlPullParserException {
		return getEventType() == START_TAG;
	}

	public boolean isEndTag() throws XmlPullParserException {
		return getEventType() == END_TAG;
	}

	public boolean isText() throws XmlPullParserException {
		return getEventType() == TEXT;
	}

	public boolean isStartDocument() throws XmlPullParserException {
		return getEventType() == START_DOCUMENT;
	}

	public boolean isEndDocument() throws XmlPullParserException {
		return getEventType() == END_DOCUMENT;
	}

	public boolean isComment() throws XmlPullParserException {
		return getEventType() == COMMENT;
	}

	public String nextTextPeacefully() {
		String text = null;

		try {

			text = nextText();

		} catch (XmlPullParserException e) {
		} catch (IOException e) {
		}

		if (text == null) {
			text = "";
		}

		return text;
	}

	/**
	 * 
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public DocumentState nextStartTag() throws XmlPullParserException,
			IOException {
		while (true) {

			int evt = next();

			if (evt == XmlPullParser.START_TAG) {
				return DocumentState.FOUNDTAG;
			}

			if (evt == XmlPullParser.END_DOCUMENT) {
				return DocumentState.END;
			}
		}

	}

	public DocumentState nextStartTagByName(String tagName)
			throws XmlPullParserException, IOException {
		while (true) {
			DocumentState documentState = nextStartTag();

			if (documentState.equals(DocumentState.END)) {
				return documentState;
			}
			if (matchCurrentTagName(tagName)) {
				break;
			}
		}

		return DocumentState.FOUNDTAG;
	}

	/**
	 * FILLTHISIN
	 * 
	 * @param parser
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public DocumentState nextEndTag() throws XmlPullParserException,
			IOException {
		while (true) {

			int evt = next();

			if (evt == XmlPullParser.END_TAG) {
				return DocumentState.FOUNDTAG;
			}

			if (evt == XmlPullParser.END_DOCUMENT) {
				return DocumentState.END;
			}
		}
	}

	public DocumentState nextEndTagByName(String tagName)
			throws XmlPullParserException, IOException {
		while (true) {
			DocumentState documentState = nextEndTag();

			if (documentState.equals(DocumentState.END)) {
				return documentState;
			}
			if (matchCurrentTagName(tagName)) {
				break;
			}
		}

		return DocumentState.FOUNDTAG;
	}

	/**
	 * FILLTHISIN
	 * 
	 * @param parser
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public boolean matchCurrentTagName(String tagName) {
		Assert.notNull(tagName, "Tag Name");
		return getName().equals(tagName);
	}

	@Override
	public void setFeature(String name, boolean state)
			throws XmlPullParserException {
		parser.setFeature(name, state);
	}

	@Override
	public boolean getFeature(String name) {
		return parser.getFeature(name);
	}

	@Override
	public void setProperty(String name, Object value)
			throws XmlPullParserException {
		parser.setProperty(name, value);
	}

	@Override
	public Object getProperty(String name) {
		return parser.getProperty(name);
	}

	@Override
	public void setInput(Reader in) throws XmlPullParserException {
		parser.setInput(in);
	}

	@Override
	public void setInput(InputStream inputStream, String inputEncoding)
			throws XmlPullParserException {
		parser.setInput(inputStream, inputEncoding);
	}

	@Override
	public String getInputEncoding() {
		return parser.getInputEncoding();
	}

	@Override
	public void defineEntityReplacementText(String entityName,
			String replacementText) throws XmlPullParserException {
		parser.defineEntityReplacementText(entityName, replacementText);
	}

	@Override
	public int getNamespaceCount(int depth) throws XmlPullParserException {
		// TODO Auto-generated method stub
		return parser.getNamespaceCount(depth);
	}

	@Override
	public String getNamespacePrefix(int pos) throws XmlPullParserException {
		return parser.getNamespacePrefix(pos);
	}

	@Override
	public String getNamespaceUri(int pos) throws XmlPullParserException {
		return parser.getNamespaceUri(pos);
	}

	@Override
	public String getNamespace(String prefix) {
		return parser.getNamespace(prefix);
	}

	@Override
	public int getDepth() {
		return parser.getDepth();
	}

	@Override
	public String getPositionDescription() {
		return parser.getPositionDescription();
	}

	@Override
	public int getLineNumber() {
		return parser.getLineNumber();
	}

	@Override
	public int getColumnNumber() {
		return parser.getColumnNumber();
	}

	@Override
	public boolean isWhitespace() throws XmlPullParserException {
		return parser.isWhitespace();
	}

	@Override
	public String getText() {
		return parser.getText();
	}

	@Override
	public char[] getTextCharacters(int[] holderForStartAndLength) {
		return parser.getTextCharacters(holderForStartAndLength);
	}

	@Override
	public String getNamespace() {
		return parser.getNamespace();
	}

	@Override
	public String getName() {
		return parser.getName();
	}

	@Override
	public String getPrefix() {
		return parser.getPrefix();
	}

	@Override
	public boolean isEmptyElementTag() throws XmlPullParserException {
		return parser.isEmptyElementTag();
	}

	@Override
	public int getAttributeCount() {
		return parser.getAttributeCount();
	}

	@Override
	public String getAttributeNamespace(int index) {
		return parser.getAttributeNamespace(index);
	}

	@Override
	public String getAttributeName(int index) {
		return parser.getAttributeName(index);
	}

	@Override
	public String getAttributePrefix(int index) {
		return parser.getAttributePrefix(index);
	}

	@Override
	public String getAttributeType(int index) {
		return parser.getAttributeType(index);
	}

	@Override
	public boolean isAttributeDefault(int index) {
		return parser.isAttributeDefault(index);
	}

	@Override
	public String getAttributeValue(int index) {
		return parser.getAttributeValue(index);
	}

	@Override
	public String getAttributeValue(String namespace, String name) {
		return parser.getAttributeValue(namespace, name);
	}

	@Override
	public int getEventType() throws XmlPullParserException {
		return parser.getEventType();
	}

	@Override
	public int next() throws XmlPullParserException, IOException {
		return parser.next();
	}

	@Override
	public int nextToken() throws XmlPullParserException, IOException {
		return parser.nextToken();
	}

	@Override
	public void require(int type, String namespace, String name)
			throws XmlPullParserException, IOException {
		parser.require(type, namespace, name);
	}

	@Override
	public String nextText() throws XmlPullParserException, IOException {
		return parser.nextText();
	}

	@Override
	public int nextTag() throws XmlPullParserException, IOException {
		return parser.nextTag();
	}
}
