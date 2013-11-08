package com.handmark.pulltorefresh.configuration.xml;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.handmark.pulltorefresh.library.internal.Assert;

class XmlPullNode {

	public static final int INFINITE = -1;

	// Must be improved about case sensitivity.
	private final Map<String, XmlPullNodeContainer> children = new TreeMap<String, XmlPullNodeContainer>(
			String.CASE_INSENSITIVE_ORDER);
	private final String tagName;
	private final XmlPullNodeCallback callback;
	private final XmlPullNodeCallback nullCallback = new XmlPullNodeCallback() {

		@Override
		public void process(XmlPullParser parser) {
		}
	};

	public XmlPullNode(String tagName) {
		this(tagName, null);
	}

	public XmlPullNode(String tagName, XmlPullNodeCallback callback) {
		Assert.notNull(tagName, "Tag Name");
		this.tagName = tagName;
		this.callback = (callback == null) ? nullCallback : callback;
	}

	public String getName() {
		return tagName;
	}

	public boolean addChildNode(XmlPullNode node) {
		return addChildNode(node, INFINITE);
	}

	public XmlPullNode getChild(String tagName) throws XmlPullParserException {
		XmlPullNodeContainer pullNodeContainer = children.get(tagName);
		if (pullNodeContainer == null) {
			return null;
		}
		return pullNodeContainer.takeXmlPullNode();

	}

	public XmlPullNodeCallback getCallback() {
		return callback;
	}

	//
	public boolean addChildNode(XmlPullNode node, int repeatLimit) {
		XmlPullNodeContainer pullNodeContainer = children.get(node.getName());
		if (pullNodeContainer != null) {
			return false;
		}

		children.put(node.getName(),
				new XmlPullNodeContainer(node, repeatLimit));
		return true;
	}

	public static interface XmlPullNodeCallback {
		void process(XmlPullParser parser) throws XmlPullParserException, IOException;
	}
	
	/**
	 * note : Justly, this Class is not thread-safe. :)
	 * @author NBP
	 *
	 */
	private static class XmlPullNodeContainer {

		private XmlPullNode node;
		private int repeatLimit; 

		public XmlPullNodeContainer(XmlPullNode node) {
			this(node, INFINITE);
		}

		public XmlPullNodeContainer(XmlPullNode node, int repeatLimit) {
			Assert.notNull(node, "XmlPullNode");
			this.node = node;
			this.repeatLimit = repeatLimit;
		}

		public XmlPullNode takeXmlPullNode() throws XmlPullParserException {
			if (repeatLimit > 0) {
				decreaseRepeatLimit();
				return node;
			}

			if (repeatLimit == 0) {
				String tagName = node.getName();
				throw new XmlPullParserException("Tag '" + tagName
						+ "' should not have more " + repeatLimit + " nodes.");
			}

			// if infinite repeats,
			return node;

		}

		private void decreaseRepeatLimit() {
			--repeatLimit;
		}
	}
}
